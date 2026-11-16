package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.SpaceInvadersGame;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;

public interface StrategyMouvement {
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game);
}
