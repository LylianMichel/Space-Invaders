package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class WallCracked implements IWallProtectector{
    private final IWallProtectector next;
    private final Sprite nextSprite;

    public WallCracked(IWallProtectector next, Sprite nextSprite){
        this.next = next;
        this.nextSprite = nextSprite;
    }

    @Override
    public void touch(Wall wall) {
        wall.setState(next);
        wall.setSprite(nextSprite);
    }
}
