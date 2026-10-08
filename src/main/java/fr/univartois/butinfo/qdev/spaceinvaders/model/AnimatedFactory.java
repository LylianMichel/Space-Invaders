package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.ISpriteStore;

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
        return null;
    }

    @Override
    public IAnimated createShot(int x, int y) {
        return null;
    }
}
