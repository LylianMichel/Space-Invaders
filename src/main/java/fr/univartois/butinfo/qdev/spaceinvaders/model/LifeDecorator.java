package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.model.animated.AbstractAnimated;
import fr.univartois.butinfo.qdev.spaceinvaders.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;

public class LifeDecorator implements IAnimated {

    private final AbstractAnimated delegate;
    private final SpaceInvadersGame game;
    private int life;

    public LifeDecorator(AbstractAnimated delegate, SpaceInvadersGame game, int life) {
        this.delegate = delegate;
        this.game = game;
        this.life = life;
        delegate.setSelf(this);
    }

    @Override
    public void onCollisionWithShot(IAnimated shot) {
        if (isDestroyed() || shot.isDestroyed()) {
            return;
        }
        if (!delegate.canBeHit()) {
            game.removeAnimated(shot);
            return;
        }
        life--;
        System.out.println("PV restants : " + life);
        delegate.onHit();
        if (life <= 0) {
            delegate.onCollisionWithShot(shot);
        } else {
            game.removeAnimated(shot);
        }
    }

    @Override public int getWidth() { return delegate.getWidth(); }
    @Override public int getHeight() { return delegate.getHeight(); }
    @Override public void setX(double x) { delegate.setX(x); }
    @Override public int getX() { return delegate.getX(); }
    @Override public DoubleProperty xProperty() { return delegate.xProperty(); }
    @Override public void setY(double y) { delegate.setY(y); }
    @Override public int getY() { return delegate.getY(); }
    @Override public DoubleProperty yProperty() { return delegate.yProperty(); }
    @Override public void setRotate(double r) { delegate.setRotate(r); }
    @Override public double getRotate() { return delegate.getRotate(); }
    @Override public DoubleProperty rotateProperty() { return delegate.rotateProperty(); }
    @Override public void setHorizontalSpeed(double s) { delegate.setHorizontalSpeed(s); }
    @Override public double getHorizontalSpeed() { return delegate.getHorizontalSpeed(); }
    @Override public void setVerticalSpeed(double s) { delegate.setVerticalSpeed(s); }
    @Override public double getVerticalSpeed() { return delegate.getVerticalSpeed(); }
    @Override public void setSprite(Sprite s) { delegate.setSprite(s); }
    @Override public Sprite getSprite() { return delegate.getSprite(); }
    @Override public ObjectProperty<Sprite> spriteProperty() { return delegate.spriteProperty(); }
    @Override public ObjectProperty<Image> imageProperty() { return delegate.imageProperty(); }
    @Override public void setDestroyed(boolean d) { delegate.setDestroyed(d); }
    @Override public boolean isDestroyed() { return delegate.isDestroyed(); }
    @Override public BooleanProperty destroyedProperty() { return delegate.destroyedProperty(); }
    @Override public void onCreation() { delegate.onCreation(); }
    @Override public void onSpawn() { delegate.onSpawn(); }
    @Override public boolean onStep(long delta) { return delegate.onStep(delta); }
    @Override public boolean isCollidingWith(IAnimated o) { return delegate.isCollidingWith(o); }
    @Override public void onCollisionWith(IAnimated o) { delegate.onCollisionWith(o); }
    @Override public void onCollisionWithBonus(Bonus b) { delegate.onCollisionWithBonus(b); }
    @Override public void onCollisionWithAlien(IAnimated a) { delegate.onCollisionWithAlien(a); }
    @Override public void onDespawn() { delegate.onDespawn(); }
    @Override public void onDestruction() { delegate.onDestruction(); }

    @Override public IAnimated self() { return delegate.self(); }
    @Override public int hashCode() { return delegate.hashCode(); }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        return obj instanceof IAnimated other && other.self() == self();
    }
}