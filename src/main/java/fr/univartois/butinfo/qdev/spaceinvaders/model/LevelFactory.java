package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.SpriteStore;

public class LevelFactory extends AnimatedFactory{

    private final int level;

    public LevelFactory(int level) {
        this.level = level;
    }

    @Override
    public IAnimated createAlien(int x,int y){
        VaisseauAlien alien = new VaisseauAlien(newgame,x,y,new SpriteStore().getSprite("alien"));

        switch (level){
            case 1:
                alien.setStrategyMouvement(new StrategyMouvementBase());
                alien.setStrategyShoot(new StrategyShootBase());
            case 2:
                alien.setStrategyMouvement(new StrategyMouvementSpeed());
                alien.setStrategyShoot(new StrategyShootIntelligent());
            case 3:
                alien.setStrategyMouvement(new StrategyMouvementAleatoire());
                alien.setStrategyShoot(new StrategyShootAleatoire());
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
