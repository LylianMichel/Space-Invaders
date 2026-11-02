package fr.univartois.butinfo.qdev.spaceinvaders.model;


import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.AbstractAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.SpaceShip;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public abstract class Bonus extends AbstractAnimated {

    /**
     * Crée une nouvelle instance de AbstractAnimated.
     *
     * @param game      Le jeu dans lequel l'objet animé évolue.
     * @param xPosition La position en x initiale de l'objet animé.
     * @param yPosition La position en y initiale de l'objet animé.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet animé.
     */
    protected Bonus(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        setVerticalSpeed(100);
    }

    @Override
    public void onCollisionWith(IAnimated other){
        if (other instanceof SpaceShip) {
            bonusEffect();
            game.removeAnimated(this);
        }
    }


    public abstract void bonusEffect();
}
