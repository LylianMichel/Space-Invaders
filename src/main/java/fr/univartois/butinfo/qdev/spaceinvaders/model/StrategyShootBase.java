package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class StrategyShootBase implements StrategyShoot{
    long maintenant;
    long lastShot;

    @Override
    public boolean onShoot(IAnimated vaisseau, SpaceInvadersGame game, Sprite sprite) {
        maintenant = System.currentTimeMillis();

        if (maintenant - lastShot > 3000) {
            lastShot=maintenant;
            game.alienShot(vaisseau);
        }
        return true;
    }
}
