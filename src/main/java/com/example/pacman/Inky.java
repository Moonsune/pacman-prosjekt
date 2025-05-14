package com.example.pacman;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import java.util.Random;

public class Inky extends Spøkelse {

    /**
     * Inky Inheritter parametre fra Spøkelse
     *
     * @param x
     * @param y
     * @param image
     */
    public Inky(double x, double y, Image image) {
        super(x, y, Color.ORANGE, image);
        imageView = new ImageView(image);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        boks = lagBoks(x, y);
        random = new Random();
        resetDirection();
    }
}




