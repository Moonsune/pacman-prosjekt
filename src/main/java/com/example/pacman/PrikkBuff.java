package com.example.pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PrikkBuff extends Objekter{
    protected Circle posisjon;

    /**
     * Klasse for Pacman sin mat Buff/Powerup. Denne extender Objekter og gjør foreløpig ingenting uten å opprette ny PrikkBuff objekt og ny Circle ved dens posisjon
     * @param x
     * @param y
     */
    public PrikkBuff(double x, double y){
        super(x, y);
        boks = lagBoks(x, y);
        posisjon = new Circle(x+10, y+10, 5.0, Color.RED);


    }
}
