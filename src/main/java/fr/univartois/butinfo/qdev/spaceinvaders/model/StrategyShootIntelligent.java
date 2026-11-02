package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.AbstractAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.SpaceShip;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class StrategyShootIntelligent implements StrategyShoot{
    protected VaisseauAlien alien;
    protected SpaceShip ship;
    protected final int ecart=10;

    public StrategyShootIntelligent(VaisseauAlien alien, SpaceShip ship) {
        this.alien=alien;
        this.ship=ship;
    }

    @Override
    public boolean onShoot() {
        return Math.abs(alien.getX() - ship.getX())<ecart;
    }

}
