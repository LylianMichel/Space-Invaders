package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.SpaceShip;
import fr.univartois.butinfo.qdev.spaceinvaders.view.ISpriteStore;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class AnimatedFactory implements IAnimatedFactory {

    private ISpriteStore newspriteStore;
    private SpaceInvadersGame newgame;

    @Override
    public void setSpriteStore(ISpriteStore spriteStore) {
        this.newspriteStore = spriteStore;
    }

    @Override
    public void setGame(SpaceInvadersGame game) {
        this.newgame = game;
    }

    @Override
    public IAnimated createAlien(int x, int y) {
        return null;
    }

    @Override
    public IAnimated createShip(int x, int y) {
        return new SpaceShip(newgame ,x , y , newspriteStore.getSprite());
    }

    @Override
    public IAnimated createShot(int x, int y) {
        return null;
    }
}
