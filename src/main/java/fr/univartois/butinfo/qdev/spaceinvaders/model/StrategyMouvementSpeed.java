package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

import java.util.Random;

public class StrategyMouvementSpeed implements StrategyMouvement {

    private static final double VITESSE_RAPIDE = 90;
    Random rand = new Random();

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        boolean limiteEcran = vaisseau.onStepBase(delta);
        if (limiteEcran) {
            vaisseau.setVerticalSpeed(0);

            double vx = vaisseau.getHorizontalSpeed();
            if (Math.abs(vx) < VITESSE_RAPIDE) {
                vaisseau.setHorizontalSpeed(Math.signum(vx) * VITESSE_RAPIDE);
            }
        }
        if (vaisseau.getY() + vaisseau.getHeight() >= game.getBottomLimit()) {
            game.alienReachedPlanet();
        }
        return limiteEcran;
    }

}