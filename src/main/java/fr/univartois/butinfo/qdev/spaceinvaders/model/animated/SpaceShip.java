package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.IAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class SpaceShip extends AbstractAnimated {

    public SpaceShip(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    @Override
    public void onCollisionWith(IAnimated other) {
        // La réaction au type de l'autre objet est gérée par son double dispatch.
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
            game.reducePlayerLife();
        }
    }
}
