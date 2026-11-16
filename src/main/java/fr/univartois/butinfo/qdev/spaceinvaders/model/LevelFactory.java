package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.SpriteStore;

import java.util.List;

public class LevelFactory extends AnimatedFactory{

    private final int level;

    public LevelFactory(int level) {
        this.level = level;
    }

    @Override
    public IAnimated createAlien(int x,int y){
        VaisseauAlien alien = new VaisseauAlien(newgame,x,y,SpriteStore.getInstance().getSprite("alien"));

        switch (level) {
            case 1:
                alien.setStrategyMouvement(StrategyMouvementBase.getInstance());
                alien.setStrategyShoot(new StrategyShootBase());
                break;
            case 2:
                MouvementCompositeEcran mouvement2 = new MouvementCompositeEcran();
                mouvement2.add(new StrategyMouvementBase());
                mouvement2.add(new StrategyMouvementSpeed());
                alien.setStrategyMouvement(mouvement2);
                alien.setStrategyShoot(new StrategyShootCompositeMoitie(
                        new StrategyShootBase(), new StrategyShootAleatoire()));
                break;
            case 3:
                MouvementCompositeAleatoire mouvement3 = new MouvementCompositeAleatoire();
                mouvement3.add(new StrategyMouvementSpeed());
                mouvement3.add(new StrategyMouvementAleatoire());
                alien.setStrategyMouvement(mouvement3);
                alien.setStrategyShoot(new StrategyShootComposite(List.of(
                        new StrategyShootAleatoire(), new StrategyShootIntelligent())));
                break;
        }

        return new LifeDecorator(alien,newgame,2);
    }

    @Override
    public IAnimated createBonus(int x,int y){
        if (level == 1) {
            return new BonusLife(newgame, x, y, newspriteStore.getSprite("ufo"));
        }
        if (level >= 3) {
            return new BonusInvulnerability(newgame, x, y, newspriteStore.getSprite("ufo"));
        }
        return super.createBonus(x, y);
    }
}
