package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;

public class MouvementCompositeEcran extends StrategyMouvementComposite{

    @Override
    protected void chooseStrategy(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        if(vaisseau.getX()< game.getWidth()/2){
            current=strategies.get(0);
        }
        else{
            current=strategies.get(1);
        }
    }

}
