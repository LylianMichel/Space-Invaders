package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.*;
import fr.univartois.butinfo.qdev.spaceinvaders.view.ISpriteStore;

import java.util.List;
import java.util.Random;

public  class AnimatedFactory implements IAnimatedFactory {

    private ISpriteStore newspriteStore;
    private SpaceInvadersGame newgame;
    private final StrategyShootComposite sharedShoot = new StrategyShootComposite(List.of(new StrategyShootBase(), new StrategyShootAleatoire(), new StrategyShootIntelligent()));
    // Une seule stratégie de mouvement pour tous les aliens : le changement de variante
    // est décidé une seule fois et s'applique à tout le monde en même temps.
    private final StrategyMouvement sharedMouvement = creerMouvementPartage();
    private static final Random rand = new Random();

    private static StrategyMouvement creerMouvementPartage() {
        MouvementCompositeAleatoire mouvement = new MouvementCompositeAleatoire();
        mouvement.add(new StrategyMouvementBase());
        mouvement.add(new StrategyMouvementAleatoire());
        return mouvement;
    }

    @Override
    public void setSpriteStore(ISpriteStore spriteStore) {
        this.newspriteStore = spriteStore;
    }

    @Override
    public void setGame(SpaceInvadersGame game) {
        this.newgame = game;
    }

    @Override
    public IAnimated createAlien(int x, int y) {
        VaisseauAlien alien= new VaisseauAlien(newgame ,x , y , newspriteStore.getSprite("alien"));
        alien.setStrategyMouvement(sharedMouvement);

        return new LifeDecorator(alien, newgame, 2);
    }


    @Override
    public IAnimated createShip(int x, int y) {
        return new LifeDecorator(new SpaceShip(newgame ,x , y , newspriteStore.getSprite("ship")), newgame, 3);
    }

    @Override
    public IAnimated createShot(int x, int y, int speed) {
        return new Shoot(newgame , x , y , newspriteStore.getSprite("shot"), speed);

    }

    @Override
    public IAnimated createBonus(int x, int y){
        if(rand.nextBoolean()){
            return new BonusLife(newgame , x , y , newspriteStore.getSprite("ufo"));
        }
        return new BonusInvulnerability(newgame , x , y , newspriteStore.getSprite("ufo"));

    }

    public IAnimated createWall(int x, int y) {
        IWallProtectector broken = new WallBroken();
        IWallProtectector cracked = new WallCracked(broken, newspriteStore.getSprite("empty-bricks"));
        IWallProtectector intact = new WallIntact(cracked, newspriteStore.getSprite("cracked-bricks"));
        return new Wall(newgame, x, y, newspriteStore.getSprite("bricks"), intact);
    }
}