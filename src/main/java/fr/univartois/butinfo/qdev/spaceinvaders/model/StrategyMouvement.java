package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.SpaceInvadersGame;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;

public interface StrategyMouvement {
    boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game);
}
