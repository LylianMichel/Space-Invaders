package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

import java.util.HashMap;
import java.util.Map;

public class StrategyShootIntelligent implements StrategyShoot {
    protected final int ecart = 10;
    private final Map<IAnimated, Long> lastShot = new HashMap<>();

    @Override
    public boolean onShoot(IAnimated vaisseau, SpaceInvadersGame game, Sprite sprite) {
        long maintenant = System.currentTimeMillis();

        if (maintenant - lastShot.getOrDefault(vaisseau, 0L) > 2000
                && Math.abs(vaisseau.getX() - game.ship.getX()) < ecart) {
            lastShot.put(vaisseau, maintenant);
            game.alienShot(vaisseau);
            return true;
        }
        return false;
    }
}