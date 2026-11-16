package fr.univartois.butinfo.qdev.spaceinvaders.model;

public class IFrameNotHittable implements IIFrameState {
    private long remaining;

    public IFrameNotHittable(long duration) { this.remaining = duration; }

    @Override
    public boolean isHittable() { return false; }
    @Override
    public IIFrameState onHit() { return this; }

    @Override
    public IIFrameState onStep(long timeDelta) {
        remaining -= timeDelta;
        return remaining <= 0 ? IFrameHittable.getInstance() : this;
    }
}
