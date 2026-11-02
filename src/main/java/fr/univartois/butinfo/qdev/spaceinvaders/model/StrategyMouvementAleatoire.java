package fr.univartois.butinfo.qdev.spaceinvaders.model;

import java.util.Random;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

public class StrategyMouvementAleatoire implements StrategyMouvement {

    private Random rand = new Random();

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        int moveMultiplier = 1;
        int tmp = rand.nextInt(100000);
        if(tmp < 50) moveMultiplier *= -1;

        boolean limiteEcran = vaisseau.onStepBase(delta);
        if (!limiteEcran) {
            vaisseau.setHorizontalSpeed(-vaisseau.getHorizontalSpeed());
            vaisseau.setVerticalSpeed(vaisseau.getVerticalSpeed() * 1.02);
        }
        else{
            vaisseau.setHorizontalSpeed(vaisseau.getHorizontalSpeed() * moveMultiplier);
        }
        if (vaisseau.getY() + vaisseau.getHeight() >= game.getBottomLimit()) {
            game.alienReachedPlanet();
        }
        return limiteEcran;
    }


}
