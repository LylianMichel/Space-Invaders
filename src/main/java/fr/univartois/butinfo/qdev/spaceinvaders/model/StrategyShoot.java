package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public interface StrategyShoot {
    boolean onShoot(IAnimated vaisseau, SpaceInvadersGame game, Sprite sprite);
}
