package fr.univartois.butinfo.qdev.spaceinvaders.model;

public interface IAliensSquadron {
    void addAlien(IAnimated alien);
    void removeAlien(IAnimated alien);
    void moveVerticaly();
}
