package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

public class StrategyMouvementBase implements StrategyMouvement{

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
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
