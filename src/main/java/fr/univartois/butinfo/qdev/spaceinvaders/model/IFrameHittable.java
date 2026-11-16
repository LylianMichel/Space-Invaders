package fr.univartois.butinfo.qdev.spaceinvaders.model;

public final class IFrameHittable implements IIFrameState {
    private static final IFrameHittable INSTANCE = new IFrameHittable();

    private IFrameHittable() {
    }

    public static IFrameHittable getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isHittable() { return true; }
    @Override
    public IIFrameState onHit() { return new IFrameNotHittable(3000); }
    @Override
    public IIFrameState onStep(long timeDelta) { return this; }
}
