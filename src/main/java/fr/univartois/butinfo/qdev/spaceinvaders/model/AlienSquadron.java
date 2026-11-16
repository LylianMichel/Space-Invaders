package fr.univartois.butinfo.qdev.spaceinvaders.model;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;

/**
 * Composite regroupant les aliens pour les déplacer en bloc.
 *
 * Pour le jeu, l'escadron est un {@link IAnimated} comme les autres :
 * - sa boîte englobante (x, y, largeur, hauteur) est calculée à partir des aliens ;
 * - lors d'une collision, il retrouve l'alien réellement touché et lui délègue.
 */
public class AlienSquadron implements IAliensSquadron, IAnimated {

    /**
     * Les aliens encore en vie.
     * CopyOnWriteArrayList : un alien peut être retiré pendant qu'on parcourt la liste
     * (par exemple lorsqu'il est tué au milieu d'une délégation de collision).
     */
    private final List<IAnimated> aliens = new CopyOnWriteArrayList<>();

    // ------------------------------------------------------------------
    // Gestion des aliens
    // ------------------------------------------------------------------

    @Override
    public void addAlien(IAnimated alien) {
        aliens.add(alien);
    }

    @Override
    public void removeAlien(IAnimated alien) {
        aliens.remove(alien);
    }

    // ------------------------------------------------------------------
    // Déplacement
    // ------------------------------------------------------------------

    @Override
    public boolean onStep(long timeDelta) {
        boolean isMaxScreen = false;
        for (IAnimated alien : aliens) {
            if (!alien.onStep(timeDelta)) {
                isMaxScreen = true;
            }
        }
        if (isMaxScreen) {
            moveVerticaly();
        }
        return false;
    }

    @Override
    public void moveVerticaly() {
        for (IAnimated alien : aliens) {
            alien.setHorizontalSpeed(-alien.getHorizontalSpeed() * 1.2);
            alien.setVerticalSpeed(15000);
        }
    }
    public void reverseHorizontally() {
        for (IAnimated alien : aliens) {
            alien.setHorizontalSpeed(-alien.getHorizontalSpeed());
        }
    }

    // ------------------------------------------------------------------
    // Boîte englobante de l'escadron
    // ------------------------------------------------------------------

    @Override
    public int getX() {
        return aliens.stream().mapToInt(IAnimated::getX).min().orElse(0);
    }

    @Override
    public int getY() {
        return aliens.stream().mapToInt(IAnimated::getY).min().orElse(0);
    }

    @Override
    public int getWidth() {
        if (aliens.isEmpty()) {
            return 0;
        }
        int right = aliens.stream().mapToInt(a -> a.getX() + a.getWidth()).max().orElse(0);
        return right - getX();
    }

    @Override
    public int getHeight() {
        if (aliens.isEmpty()) {
            return 0;
        }
        int bottom = aliens.stream().mapToInt(a -> a.getY() + a.getHeight()).max().orElse(0);
        return bottom - getY();
    }

    // ------------------------------------------------------------------
    // Collisions
    // ------------------------------------------------------------------

    /**
     * Cherche l'alien qui touche réellement l'objet donné.
     * La boîte englobante de l'escadron peut contenir des "trous" (aliens déjà
     * tués) : un objet peut donc être dans la boîte sans toucher aucun alien.
     */
    private Optional<IAnimated> alienTouchedBy(IAnimated other) {
        return aliens.stream().filter(alien -> alien.isCollidingWith(other)).findFirst();
    }

    @Override
    public boolean isCollidingWith(IAnimated other) {
        return alienTouchedBy(other).isPresent();
    }

    @Override
    public void onCollisionWith(IAnimated other) {
        alienTouchedBy(other).ifPresent(alien -> alien.onCollisionWith(other));
    }

    @Override
    public void onCollisionWithShot(IAnimated shot) {
        alienTouchedBy(shot).ifPresent(alien -> alien.onCollisionWithShot(shot));
    }

    @Override
    public void onCollisionWithBonus(Bonus bonus) {
        // Rien à faire.
    }

    @Override
    public void onCollisionWithAlien(IAnimated alien) {
        // Rien à faire.
    }

    // ------------------------------------------------------------------
    // Cycle de vie
    // ------------------------------------------------------------------

    @Override
    public void onCreation() {
    }

    @Override
    public void onSpawn() {
    }

    @Override
    public void onDespawn() {
        aliens.forEach(IAnimated::onDespawn);
    }

    @Override
    public void onDestruction() {
        // Appelé par le jeu lors d'un redémarrage : on nettoie les aliens survivants.
        aliens.forEach(IAnimated::onDestruction);
    }

    /**
     * Un escadron sans alien est considéré comme détruit : cela évite toute
     * collision (et tout calcul de boîte) sur un escadron vide.
     */
    @Override
    public boolean isDestroyed() {
        return aliens.isEmpty();
    }

    @Override
    public void setDestroyed(boolean destroyed) {
    }

    @Override
    public IAnimated self() {
        return this;
    }

    // ------------------------------------------------------------------
    // Méthodes sans objet pour un composite
    // ------------------------------------------------------------------

    @Override
    public void setX(double xPosition) {
    }

    @Override
    public DoubleProperty xProperty() {
        return null;
    }

    @Override
    public void setY(double yPosition) {
    }

    @Override
    public DoubleProperty yProperty() {
        return null;
    }

    @Override
    public void setRotate(double rotate) {
    }

    @Override
    public double getRotate() {
        return 0;
    }

    @Override
    public DoubleProperty rotateProperty() {
        return null;
    }

    @Override
    public void setHorizontalSpeed(double speed) {
    }

    @Override
    public double getHorizontalSpeed() {
        return 0;
    }

    @Override
    public void setVerticalSpeed(double speed) {
    }

    @Override
    public double getVerticalSpeed() {
        return 0;
    }

    @Override
    public void setSprite(Sprite sprite) {
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public ObjectProperty<Sprite> spriteProperty() {
        return null;
    }

    @Override
    public ObjectProperty<Image> imageProperty() {
        return null;
    }

    @Override
    public BooleanProperty destroyedProperty() {
        return null;
    }
}