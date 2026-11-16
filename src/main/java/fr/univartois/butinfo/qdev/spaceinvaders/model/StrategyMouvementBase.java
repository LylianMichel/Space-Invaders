package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

public class StrategyMouvementBase implements StrategyMouvement{

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        boolean limiteEcran = vaisseau.onStepBase(delta);
        if (!limiteEcran) {
//            vaisseau.setHorizontalSpeed(-vaisseau.getHorizontalSpeed() * 1.02);
//            vaisseau.setVerticalSpeed(20000);
        }
        else{
            vaisseau.setVerticalSpeed(vaisseau.getVerticalSpeed() * 0);
        }
        if (vaisseau.getY() + vaisseau.getHeight() >= game.getBottomLimit()) {
            game.alienReachedPlanet();
        }
        return limiteEcran;
    }
}
