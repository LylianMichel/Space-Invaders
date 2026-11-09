package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.*;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class SpaceShip extends AbstractAnimated {

    private IIFrameState iFrameState = new IFrameHittable();

    public SpaceShip(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    @Override
    public boolean onStep(long timeDelta) {
        iFrameState = iFrameState.onStep(timeDelta);
        return super.onStep(timeDelta);
    }

    @Override
    public void onCollisionWith(IAnimated other) {
        // Géré par double dispatch.
    }

    @Override
    public void onCollisionWithAlien(IAnimated alien) {
        if (!isDestroyed() && !alien.isDestroyed()) {
            game.playerIsDead();
        }
    }

    @Override
    public void onCollisionWithShot(IAnimated shot) {
        if (!isDestroyed() && !shot.isDestroyed()) {
            game.removeAnimated(shot);
            if (iFrameState.isHittable()) {
                iFrameState = iFrameState.onHit();
                game.reducePlayerLife();
            }
        }
    }

    @Override
    public void onCollisionWithBonus(Bonus bonus) {
        if (!isDestroyed() && !bonus.isDestroyed()) {
            bonus.bonusEffect(this);
            game.removeAnimated(bonus);
        }
    }

    public void gainInvulnerability() {
        iFrameState = new IFrameNotHittable(5000);
    }
}