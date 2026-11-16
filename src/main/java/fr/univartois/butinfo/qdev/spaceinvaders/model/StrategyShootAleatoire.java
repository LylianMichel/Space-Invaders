package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StrategyShootAleatoire implements StrategyShoot {
    private final Random random = new Random();
    private final Map<IAnimated, Long> nextShot = new HashMap<>();

    private long nextDelay() {
        return 1000 + random.nextInt(4000); // entre 1s et 5s
    }

    @Override
    public boolean onShoot(IAnimated vaisseau, SpaceInvadersGame game, Sprite sprite) {
        long now = System.currentTimeMillis();
        long prochain = nextShot.computeIfAbsent(vaisseau, v -> now + nextDelay());

        if (now >= prochain) {
            nextShot.put(vaisseau, now + nextDelay());
            game.alienShot(vaisseau);
            return true;
        }
        return false;
    }
}