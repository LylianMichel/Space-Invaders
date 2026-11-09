package fr.univartois.butinfo.qdev.spaceinvaders.model;

import javafx.beans.property.IntegerProperty;

public interface IIFrameState {
    boolean isHittable();
    IIFrameState onHit();                 // retourne le nouvel état
    IIFrameState onStep(long timeDelta);  // retourne le nouvel état
}
