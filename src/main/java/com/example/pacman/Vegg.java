package com.example.pacman;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Vegg extends Objekter {

    /**
     * Konstruktør for Vegg
     * @param x
     * @param y
     */
    public Vegg (Double x, double y) {
        super(x, y);
        boks = lagBoks(x,y);
    }

    /**
     * Metode som lager vegg inn i JavaFX
     * @param farge Farge for Color variabel for valg av farge
     * @return returnerer Rektangel objekt
     */
    public Rectangle lagVegg(Color farge) {
        Rectangle bounding = new Rectangle(x, y, 20, 20);
        bounding.setStroke(farge);
        return bounding;
    }
}

