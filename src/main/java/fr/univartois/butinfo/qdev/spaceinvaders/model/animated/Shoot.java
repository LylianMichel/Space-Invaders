package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.IAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

/** Tir qui monte et disparaît aux limites de l'écran ou lors d'une collision. */
public class Shoot extends AbstractAnimated {

    public Shoot(SpaceInvadersGame game, double x, double y, Sprite sprite, int speed) {
        super(game, x, y, sprite);
        setVerticalSpeed(speed);
    }

    @Override
    public boolean onStep(long delta) {
        boolean moved = super.onStep(delta);
        if (!moved && !isDestroyed()) {
            game.removeAnimated(this);
        }
        return moved;
    }

    @Override
    public void onCollisionWith(IAnimated other) {
        if (!isDestroyed() && !other.isDestroyed()) {
            other.onCollisionWithShot(this);
        }
    }

    @Override
    public void onCollisionWithAlien(IAnimated alien) {
        if (!isDestroyed() && !alien.isDestroyed()) {
            game.removeAnimated(alien);
            game.alienIsDead(alien);
            game.removeAnimated(this);
        }
    }

    @Override
    public void onCollisionWithShot(IAnimated shot) {
        if (!isDestroyed() && !shot.isDestroyed()) {
            game.removeAnimated(shot);
//            game.alienIsDead(shot);
            game.removeAnimated(this);
        }
    }
}
