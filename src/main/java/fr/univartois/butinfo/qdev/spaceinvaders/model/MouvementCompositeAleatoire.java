package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;

import java.util.Random;

/**
 * Stratégie de mouvement qui change aléatoirement de variante toutes les
 * {@link #DELAI} millisecondes.
 *
 * Cette instance est faite pour être PARTAGÉE par tous les aliens : {@code onStep}
 * est appelée une fois par alien et par frame, donc le délai est mesuré avec
 * l'horloge (et non en cumulant {@code delta}, qui s'écoulerait N fois trop vite
 * avec N aliens). Le premier alien de la frame qui voit le délai dépassé fait
 * le changement, et tous les suivants utilisent déjà la nouvelle variante.
 */
public class MouvementCompositeAleatoire extends StrategyMouvementComposite {

    // Le délai avant le changement de variante (en millisecondes).
    private static final long DELAI = 3000;

    // L'instant du dernier changement de variante.
    private long dernierChangement = System.currentTimeMillis();

    private final Random random = new Random();

    @Override
    protected void chooseStrategy(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        long maintenant = System.currentTimeMillis();
        if (maintenant - dernierChangement >= DELAI) {
            dernierChangement = maintenant;
            current = strategies.get(random.nextInt(strategies.size()));
        }
    }

}