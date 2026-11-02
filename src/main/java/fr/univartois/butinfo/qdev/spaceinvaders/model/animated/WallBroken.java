package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class WallBroken implements IWallProtectector{
    private final IWallProtectector next;
    private final Sprite nextSprite;

    public WallBroken(IWallProtectector next, Sprite nextSprite){
        this.next = next;
        this.nextSprite = nextSprite;
    }

    @Override
    public void touch(Wall wall) {
        wall.getGame().removeAnimated(wall); // le mur disparait
    }
}
