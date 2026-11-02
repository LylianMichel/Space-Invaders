package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

@StrategyDesignPattern(strategy = StrategyMouvement.class, participant = StrategyParticipant.INTERFACE)
public interface StrategyMouvement {
    public boolean onStep(long delta);
}
