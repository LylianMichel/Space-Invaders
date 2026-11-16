package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

import java.util.Random;

public class StrategyMouvementSpeed implements StrategyMouvement {

    Random rand = new Random();

    public void attaquer(VaisseauAlien vaisseau) {
        vaisseau.setVerticalSpeed(150);
    }

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        int tmp = rand.nextInt(100000);
        if(tmp < 50) attaquer(vaisseau);

        boolean limiteEcran = vaisseau.onStepBase(delta);

        if (!limiteEcran) {
            vaisseau.setHorizontalSpeed(-vaisseau.getHorizontalSpeed());
            vaisseau.setVerticalSpeed(vaisseau.getVerticalSpeed() * 1.02);
        }

        if (vaisseau.getY() + vaisseau.getHeight() >= game.getBottomLimit()) {
            game.alienReachedPlanet();
        }

        return limiteEcran;
    }

}