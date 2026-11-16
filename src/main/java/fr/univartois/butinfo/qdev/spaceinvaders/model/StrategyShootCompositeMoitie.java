package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class StrategyShootCompositeMoitie implements StrategyShoot {
    private final StrategyShoot gauche;
    private final StrategyShoot droite;

    public StrategyShootCompositeMoitie(StrategyShoot gauche, StrategyShoot droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public boolean onShoot(IAnimated vaisseau, SpaceInvadersGame game, Sprite sprite) {
        StrategyShoot choisie = vaisseau.getX() < game.getWidth() / 2 ? gauche : droite;
        return choisie.onShoot(vaisseau, game, sprite);
    }
}