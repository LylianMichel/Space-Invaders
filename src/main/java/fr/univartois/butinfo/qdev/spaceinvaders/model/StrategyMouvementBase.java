package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

@StrategyDesignPattern(strategy = StrategyMouvement.class, participant = StrategyParticipant.IMPLEMENTATION)
public class StrategyMouvementBase implements StrategyMouvement {
    private final VaisseauAlien alien;

    public StrategyMouvementBase(VaisseauAlien alien) {
        this.alien = alien;
    }

    @Override
    public boolean onStep(long delta) {
        boolean limiteEcran = alien.deplacer(delta);
        if (!limiteEcran) {
            alien.setHorizontalSpeed(-alien.getHorizontalSpeed());
            alien.setVerticalSpeed(alien.getVerticalSpeed() * 1.02);
        }
        return limiteEcran;
    }
}
