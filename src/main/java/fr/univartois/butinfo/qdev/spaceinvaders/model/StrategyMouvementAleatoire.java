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
        tirerInversion(game);

        boolean limiteEcran = vaisseau.onStepBase(delta);
        if (limiteEcran) {
            // Comme StrategyMouvementBase : la descente demandée par l'escadron
            // n'est qu'une impulsion d'une frame.
            vaisseau.setVerticalSpeed(0);
        }
        // Si limiteEcran est faux, l'alien a touché un bord : AlienSquadron inverse
        // le sens de tous les aliens et les fait descendre, rien à faire ici.

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