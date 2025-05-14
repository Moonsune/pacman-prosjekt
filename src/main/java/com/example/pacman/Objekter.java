package com.example.pacman;

import javafx.geometry.BoundingBox;

public abstract class Objekter {
    protected double x, y;
    protected BoundingBox boks;

    /**
     * Hoved Super klasse for alle klasser som går under objekter.
     * @param x X-verdi
     * @param y Y-verdi
     */
    public Objekter(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Metode som lager Bounding box basert på X og Y
     * @param x X-verdi
     * @param y Y-verdi
     * @return Returnerer ny Bounding Box objekt
     */
    public BoundingBox lagBoks(double x, double y){
        boks = new BoundingBox(x, y, 20, 20);
        return boks;
    }
}
