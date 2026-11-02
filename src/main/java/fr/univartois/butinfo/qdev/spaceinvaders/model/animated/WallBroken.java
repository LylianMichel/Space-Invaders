package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

public class WallBroken implements IWallProtectector{

    @Override
    public void touch(Wall wall) {
        wall.getGame().removeAnimated(wall); // le mur disparait
    }
}
