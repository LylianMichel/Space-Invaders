package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.*;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class VaisseauAlien extends AbstractAnimated {
    public StrategyShoot strategyShoot;
    public StrategyMouvement strategyMouvement;


    public VaisseauAlien(SpaceInvadersGame game, double x, double y, Sprite sprite) {
        super(game, x, y, sprite);
        setHorizontalSpeed(75);
        setVerticalSpeed(10);
        setStrategyShoot(new StrategyShootBase());
        setStrategyMouvement(new StrategyMouvementBase());
    }

    public boolean onStepBase(long delta) {
        return super.onStep(delta);
    }

    @Override
    public void onCollisionWith(IAnimated other) {
        if (!isDestroyed() && !other.isDestroyed()) {
            other.onCollisionWithAlien(self);
        }
    }

    @Override
    public void onCollisionWithShot(IAnimated shot) {
        if (!isDestroyed() && !shot.isDestroyed()) {
            game.removeAnimated(shot);
            game.alienIsDead(self);   // retire l'alien, met à jour le score
        }
    }

    @Override
    public boolean onStep(long delta) {
        onShot();
        return strategyMouvement.onStep(this, delta, game);
    }

    public boolean onShot() {
        return strategyShoot.onShoot(self, game, sprite.get());
    }

    public void setStrategyShoot(StrategyShoot strategy){
        strategyShoot = strategy;
    }

    public void setStrategyMouvement(StrategyMouvement strategy){
        strategyMouvement = strategy;
    }
}
