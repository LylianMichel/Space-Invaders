package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.AbstractAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.SpaceShip;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class StrategyShootIntelligent implements StrategyShoot{
    protected final int ecart=10;
    long maintenant;
    long lastShot;

    @Override
    public boolean onShoot(IAnimated vaisseau, SpaceInvadersGame game, Sprite sprite) {
        maintenant = System.currentTimeMillis();

        if (maintenant - lastShot > 2000 && Math.abs(vaisseau.getX() - game.ship.getX())<ecart) {
            lastShot=maintenant;
            game.alienShot(vaisseau);
            return true;
        }
        return false;
    }

}
