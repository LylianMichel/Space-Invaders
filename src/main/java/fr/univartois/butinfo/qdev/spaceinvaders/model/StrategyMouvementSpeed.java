package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.VaisseauAlien;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class StrategyMouvementSpeed implements StrategyMouvement {

    private boolean attaque = false;

    public void attaquer(VaisseauAlien vaisseau) {
        attaque = true;
        vaisseau.setVerticalSpeed(150);
    }

    @Override
    public boolean onStep(VaisseauAlien vaisseau, long delta, SpaceInvadersGame game) {
        if (attaque) {
            vaisseau.setVerticalSpeed(150);
        }

        boolean limiteEcran = vaisseau.onStepBase(delta);

        if (!limiteEcran && !attaque) {
            vaisseau.setHorizontalSpeed(-vaisseau.getHorizontalSpeed());
            vaisseau.setVerticalSpeed(vaisseau.getVerticalSpeed() * 1.02);
        }

        if (vaisseau.getY() + vaisseau.getHeight() >= game.getBottomLimit()) {
            game.alienReachedPlanet();
        }

        return limiteEcran;
    }

}