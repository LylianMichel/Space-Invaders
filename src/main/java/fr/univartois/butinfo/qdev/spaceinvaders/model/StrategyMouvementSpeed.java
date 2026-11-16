package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.SpaceInvadersGame;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class StrategyMouvementSpeed extends VaisseauAlien {
    public StrategyMouvementSpeed(SpaceInvadersGame game, double x, double y, Sprite sprite) {
        super(game, x, y, sprite);
    }

    private boolean attaque = false;

    public void attaquer() {
        attaque = true;
        setVerticalSpeed(150);
    }

    @Override
    public boolean onStep(long delta) {
        if (attaque) {
            setVerticalSpeed(150);
        }

        boolean limiteEcran = move(delta);

        if (!limiteEcran && !attaque) {
            setHorizontalSpeed(-getHorizontalSpeed());
            setVerticalSpeed(getVerticalSpeed() * 1.02);
        }

        if (getY() + getHeight() >= game.getBottomLimit()) {
            game.alienReachedPlanet();
        }

        return limiteEcran;
    }

}
