package fr.univartois.butinfo.qdev.spaceinvaders.model;

import java.util.Random;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

@StrategyDesignPattern(strategy = StrategyMouvement.class, participant = StrategyParticipant.IMPLEMENTATION)
public class StrategyMouvementAleatoire implements StrategyMouvement {

    private final VaisseauAlien alien;
    private final Random random = new Random();
    private long tempsEcoule = 500;

    public StrategyMouvementAleatoire(VaisseauAlien alien) {
        this.alien = alien;
    }

    @Override
    public boolean onStep(long delta) {
        tempsEcoule += delta;

        // Choisir de nouvelles vitesses toutes les 500 ms.
        if (tempsEcoule >= 500) {
            tempsEcoule %= 500;

            // Vitesse horizontale comprise entre -100 et 100.
            alien.setHorizontalSpeed(random.nextInt(201) - 100);

            // Descente à une vitesse comprise entre 10 et 40.
            alien.setVerticalSpeed(random.nextInt(31) + 10);
        }

        boolean deplace = alien.deplacer(delta);

        // Repartir dans l'autre sens si un bord bloque le déplacement.
        if (!deplace) {
            alien.setHorizontalSpeed(-alien.getHorizontalSpeed());
        }

        return deplace;
    }
}
