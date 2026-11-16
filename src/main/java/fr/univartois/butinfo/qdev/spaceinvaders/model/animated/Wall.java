package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.IAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;


public class Wall extends AbstractAnimated {
    private IWallProtectector state;

    public Wall(SpaceInvadersGame game, double x, double y, Sprite sprite, IWallProtectector state) {
        super(game, x, y, sprite);
        this.state = state;
    }

    public void setState(IWallProtectector state) {
        this.state = state;
    }

    public SpaceInvadersGame getGame() {
        return game;
    }

    @Override
    public void onCollisionWith(IAnimated other) {

    }

    @Override
    public void onCollisionWithShot(IAnimated shot){
        if(!isDestroyed() && !shot.isDestroyed()){
            game.removeAnimated(shot);
            state.touch(this);
        }
    }

    @Override
    public void onCollisionWithAlien(IAnimated alien){
        if(!isDestroyed()){
            game.removeAnimated(this);
        }
    }
}
