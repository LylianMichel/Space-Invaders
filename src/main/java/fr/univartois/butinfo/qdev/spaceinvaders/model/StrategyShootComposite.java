package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

import java.util.List;
import java.util.Random;

public class StrategyShootComposite implements StrategyShoot{
    private final List<StrategyShoot> variantes;
    private StrategyShoot courante;
    private final Random random = new Random();
    private int nbTirs = 0;

    public StrategyShootComposite(List<StrategyShoot> variantes){
        this.variantes = variantes;
        this.courante = variantes.get(0);
    }
    @Override
    public boolean onShoot(IAnimated vaisseau, SpaceInvadersGame game, Sprite sprite) {
        boolean tirAlien = courante.onShoot(vaisseau, game, sprite);

        if(tirAlien){
            nbTirs++;
        }

        if(nbTirs >= 5){
            nbTirs = 0;
            courante = variantes.get(random.nextInt(variantes.size()));
        }

        return tirAlien;
    }
}
