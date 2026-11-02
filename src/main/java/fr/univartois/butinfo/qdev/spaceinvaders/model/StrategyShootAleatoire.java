package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

import java.util.Random;

public class StrategyShootAleatoire implements StrategyShoot{
    private final Random random = new Random();
    private long lastshot = System.currentTimeMillis();
    private long delay = nextDelay();

    private long nextDelay() {
        return 1000 + random.nextInt(4000); // entre 1s et 5s
    }

    @Override
    public boolean onShoot(VaisseauAlien vaisseau, SpaceInvadersGame game, Sprite sprite) {
        long now = System.currentTimeMillis();

        if(now - lastshot > delay){
            lastshot = now;
            delay = nextDelay();
            game.alienShot(vaisseau);
        }
        return true;
    }
}
