package fr.univartois.butinfo.qdev.spaceinvaders.model.animated;

import fr.univartois.butinfo.qdev.spaceinvaders.model.IAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class VaisseauAlien extends AbstractAnimated {

    public VaisseauAlien(SpaceInvadersGame game, double x, double y, Sprite sprite) {
        super(game, x, y, sprite);
        setHorizontalSpeed(75);
        setVerticalSpeed(1);
    }

    @Override
    public boolean onStep(long delta) {
        boolean limiteEcran = super.onStep(delta);
        if (limiteEcran) {
            setHorizontalSpeed(-getHorizontalSpeed());
            setVerticalSpeed(getVerticalSpeed() * 1.02);
        }
        if (getY() + getHeight() >= game.getBottomLimit()) {
            game.alienReachedPlanet();
        }
        return limiteEcran;
    }

    @Override
    public void onCollisionWith(IAnimated other) {
        //ne fait rien
        }
}
