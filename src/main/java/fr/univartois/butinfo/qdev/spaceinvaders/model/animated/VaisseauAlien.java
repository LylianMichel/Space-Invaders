package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.*;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class VaisseauAlien extends AbstractAnimated {
    public StrategyShoot strategyShoot;
        public StrategyMouvement strategyMouvement;


    public VaisseauAlien(SpaceInvadersGame game, double x, double y, Sprite sprite) {
        super(game, x, y, sprite);
        setHorizontalSpeed(75);
        setVerticalSpeed(1);
        setStrategyShoot(new StrategyShootBase(game, x, y, sprite));
        setStrategyMouvement(new StrategyMouvementBase());
    }

    @Override
    public void onCollisionWith(IAnimated other) {
        if (!isDestroyed() && !other.isDestroyed()) {
            other.onCollisionWithAlien(this);
        }
    }

    @Override
    public void onCollisionWithShot(IAnimated shot) {
        if (!isDestroyed() && !shot.isDestroyed()) {
            game.alienIsDead(this);
            game.removeAnimated(shot);
        }
    }

    @Override
    public boolean onStep(long delta) {
        return strategyMouvement.onStep(this, delta, game);
    }

    public boolean onShot(){
        return strategyShoot.onShoot();
    }

    public void setStrategyShoot(StrategyShoot strategy){
        strategyShoot = strategy;
    }

    public void setStrategyMouvement(StrategyMouvement strategy){
        strategyMouvement = strategy;
    }
}
