package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;

import java.util.ArrayList;
import java.util.List;

public abstract class StrategyMouvementComposite implements StrategyMouvement {
    protected final List<StrategyMouvement> strategies = new ArrayList<>();
    protected StrategyMouvement current;

    public void add(StrategyMouvement strategy) {
        strategies.add(strategy);
        if (current == null) {
            current = strategy;
        }
    }

    protected abstract void chooseStrategy(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game);

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game){
        chooseStrategy(vaisseau, delta, game);
        return current.onStep(vaisseau, delta, game);
    }

}
