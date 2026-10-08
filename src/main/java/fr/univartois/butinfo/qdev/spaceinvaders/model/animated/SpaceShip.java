package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.IAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class SpaceShip extends AbstractAnimated {
    public SpaceShip(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite){
        super(game, xPosition, yPosition, sprite);
    }

    public void onCollisionWith(IAnimated other){

    }
}
