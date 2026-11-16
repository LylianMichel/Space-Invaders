package fr.univartois.butinfo.qdev.spaceinvaders.model;

public final class StrategyShootBase implements StrategyShoot {

    private static final StrategyShootBase INSTANCE = new StrategyShootBase();

    private StrategyShootBase() {
    }

    public static StrategyShootBase getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean onShoot() {
        return false;
    }
}
