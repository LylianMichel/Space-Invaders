package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.IAnimated;

public class VaisseauAlien extends AbstractAnimated{

    public VaisseauAlien(SpaceInvadersGame game, double x, double y, Sprite sprite) {
        super(game, x, y, sprite);
        setHorizontalSpeed(75);
        setVerticalSpeed(1);
    }

    @Override
    public boolean onStep(long delta){
        boolean limiteEcran=super.delta;
        if(limiteEcran){
            setHorizontalSpeed(-getHorizontalSpeed());
            setVerticalSpeed(getVerticalSpeed() * 1.02);
        }
        if (game.getBottomLimit()) {
            game.alienReachedPlanet();
        }
    }

    @Override
    public void isCollidingWith(IAnimated other){ }

    public void playerIsDead() {
        animation.stop();
        controller.gameOver("Le joeur est mort!");
    }

    public void alienReachedPlanet() {
        animation.stop();
        controller.gameOver("Les aliens ont envahi la planète!");
    }


}
