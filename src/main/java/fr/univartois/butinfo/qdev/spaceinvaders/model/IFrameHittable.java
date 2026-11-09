package fr.univartois.butinfo.qdev.spaceinvaders.model;

import fr.univartois.butinfo.qdev.spaceinvaders.view.SpriteStore;
import javafx.beans.property.IntegerProperty;

public class IFrameHittable implements IIFrameState {
    @Override
    public boolean isHittable() { return true; }
    @Override
    public IIFrameState onHit() { return new IFrameNotHittable(3000); }
    @Override
    public IIFrameState onStep(long timeDelta) { return this; }
}
