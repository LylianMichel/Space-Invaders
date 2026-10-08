
package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.IAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class Shoot extends AbstractAnimated {


    public Shoot(SpaceInvadersGame game, double x, double y, Sprite sprite) {
        super(game, x, y, sprite);
        setVerticalSpeed(-300);
    }


    @Override
    public void onCollisionWith(IAnimated other) {
        game.removeAnimated(this);
    }

    @Override
    public void onCollisionWithAlien(IAnimated alien) {

    }


    @Override
    public void onCollisionWithShot(IAnimated shot) {

    }
    @Override
    public boolean onStep(long delta) {
        boolean moved = super.onStep(delta);

        if (!moved && !isDestroyed()) {
            game.removeAnimated(this);
        }

        return moved;
    }

}
