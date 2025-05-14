package com.example.pacman;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import java.util.Random;


public class Blinky extends Spøkelse {
    private PacMan pacman;

    /**
     *Blinky Inheritter variabler fra Spøkelse
     * @param x
     * @param y
     * @param image
     */
    public Blinky(double x, double y, Image image) {
        super(x, y, Color.ORANGE, image);
        imageView = new ImageView(image);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        boks = lagBoks(x, y);
        random = new Random();
        resetDirection();
    }
}
