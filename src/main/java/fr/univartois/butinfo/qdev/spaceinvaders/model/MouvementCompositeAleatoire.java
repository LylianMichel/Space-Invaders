package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;

import java.util.Random;

public class MouvementCompositeAleatoire extends StrategyMouvementComposite{
    //le delai avant le changement de variante
    private static final long DELAI = 3000;
    //le temps qui s'ecoule
    private long tempsEcoule=0;
    private final Random random = new Random();

    @Override
    protected void chooseStrategy(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        tempsEcoule=tempsEcoule+delta;
        if(tempsEcoule>=DELAI){
            tempsEcoule=0;
            current = strategies.get(random.nextInt(strategies.size()));
        }
    }

}
