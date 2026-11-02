package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;

public class BonusLife extends Bonus{
    /**
     * Crée une nouvelle instance de AbstractAnimated.
     *
     * @param game      Le jeu dans lequel l'objet animé évolue.
     * @param xPosition La position en x initiale de l'objet animé.
     * @param yPosition La position en y initiale de l'objet animé.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet animé.
     */
    protected BonusLife(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    @Override
    public void bonusEffect(){

    }
}
