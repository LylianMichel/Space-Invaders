package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

public final class WallBroken implements IWallProtectector{

    private static final WallBroken INSTANCE = new WallBroken();

    private WallBroken() {
    }

    public static WallBroken getInstance() {
        return INSTANCE;
    }

    @Override
    public void touch(Wall wall) {
        wall.getGame().removeAnimated(wall); // le mur disparait
    }
}
