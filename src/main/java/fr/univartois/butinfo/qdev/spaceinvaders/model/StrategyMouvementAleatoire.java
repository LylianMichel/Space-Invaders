package fr.univartois.butinfo.qdev.spaceinvaders.model;

import java.util.Random;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyDesignPattern;
import fr.univartois.dpprocessor.designpatterns.strategy.StrategyParticipant;

public class StrategyMouvementAleatoire implements StrategyMouvement {

    private Random random = new Random();

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        boolean limiteEcran = vaisseau.onStep(delta);
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
