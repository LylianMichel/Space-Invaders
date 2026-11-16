package fr.univartois.butinfo.qdev.spaceinvaders.model;

import java.util.Random;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

/**
 * Variante de mouvement où l'escadron change aléatoirement de sens.
 *
 * Cette instance est PARTAGÉE par tous les aliens : {@code onStep} est appelée une
 * fois par alien et par frame. Le tirage est donc limité à un par
 * {@link #INTERVALLE_TIRAGE} millisecondes (quel que soit le nombre d'aliens), et
 * l'inversion s'applique à tout l'escadron d'un coup, pour qu'il reste groupé.
 *
 * Les rebonds sur les bords sont gérés par {@link AlienSquadron}, pas ici.
 */
public class StrategyMouvementAleatoire implements StrategyMouvement {

    // Délai minimal entre deux tirages (en millisecondes).
    private static final long INTERVALLE_TIRAGE = 100;

    // Probabilité (en %) d'inverser le sens de l'escadron à chaque tirage.
    private static final int PROBA_INVERSION = 2;

    private final Random rand = new Random();

    private long dernierTirage = System.currentTimeMillis();

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        boolean limiteEcran = vaisseau.onStepBase(delta);

        if (limiteEcran) {
            // L'alien a pu bouger : on annule la descente donnée par l'escadrille
            vaisseau.setVerticalSpeed(0);

            // De temps en temps, l'alien change de direction au hasard
            if (rand.nextInt(100000) < 50) {
                vaisseau.setHorizontalSpeed(-vaisseau.getHorizontalSpeed());
            }
        }
        // Si l'alien est au bord (limiteEcran == false), on ne fait rien :
        // c'est l'escadrille qui gère le demi-tour et la descente.

        if (vaisseau.getY() + vaisseau.getHeight() >= game.getBottomLimit()) {
            game.alienReachedPlanet();
        }

        return limiteEcran;
    }

    /**
     * Tire au sort (au plus une fois par intervalle) si tout l'escadron doit changer
     * de sens.
     */
    private void tirerInversion(SpaceInvadersGame game) {
        long maintenant = System.currentTimeMillis();
        if (maintenant - dernierTirage < INTERVALLE_TIRAGE) {
            return;
        }
        dernierTirage = maintenant;
        if (rand.nextInt(100) < PROBA_INVERSION) {
            game.reverseAliens();
        }
    }

}