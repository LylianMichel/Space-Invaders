package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;
import java.util.HashMap;
import java.util.Map;

public class StrategyShootBase implements StrategyShoot{
    private final Map<IAnimated, Long> lastShot = new HashMap<>();

    @Override
    public boolean onShoot(IAnimated vaisseau, SpaceInvadersGame game, Sprite sprite) {
        long maintenant = System.currentTimeMillis();

        if (maintenant - lastShot.getOrDefault(vaisseau, 0L) > 3000) {
            lastShot.put(vaisseau, maintenant);
            game.alienShot(vaisseau);
            return true;
        }
        return false;
    }
}
