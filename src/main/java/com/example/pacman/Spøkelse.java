package com.example.pacman;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Random;

public abstract class Spøkelse extends Objekter {
    protected Color farge;
    protected ImageView imageView;
    protected Retning retning;
    protected Random random;
    protected int dx, dy;
    protected PacMan pacman;

    /**
     * Konstruktør for den abstrakte Spøkelsesklassen.
     * @param x Double verdi for X,
     * @param y Double verdi for Y
     * @param farge farge verdi ifra Color klassen i JavaFX
     * @param image bilde fra Image klassen i JavaFX
     */
    public Spøkelse(double x, double y, Color farge, Image image) {
        super(x, y);
        this.farge = farge;
        this.imageView = new ImageView(image);
        this.imageView.setTranslateX(x);
        this.imageView.setTranslateY(y);
        boks = lagBoks(x, y);
        random = new Random();
        resetDirection();
    }

    /**
     * Beveg metode som da flytter på spøkelsene
     */
    protected void beveg() {
        double nextX = getX() + dx * Spill.PIXEL, nextY = getY() + dy * Spill.PIXEL;

        if (!checkWallCollision(nextX, nextY)) {
            setX(nextX);
            setY(nextY);
        } else {
            resetDirection();

            nextX = getX() + dx * Spill.PIXEL;
            nextY = getY() + dy * Spill.PIXEL;

            if (!checkWallCollision(nextX, nextY)) {
                setX(nextX);
                setY(nextY);
            }
        }
    }

    /**
     * Resetter hvilken retrning Spøkelse går mot
     */
    protected void resetDirection() {
        dx = random.nextInt(3) - 1;
        dy = random.nextInt(3) -1;

        while (dx == 0 && dy == 0) {
            dx = random.nextInt(3) - 1;
            dy = random.nextInt(3) - 1;
        }
    }


    /**
     * Metode som tar inn X og Y verdi som sjekker om neste X og Y kordinat kolliderer med vegg.
     * @param dx double verdi for X kordinat
     * @param dy double verdi for Y kordinat
     */
    protected void move(double dx, double dy) {
        double nextX = getX() + dx;
        double nextY = getY() + dy;

        if (!checkWallCollision(nextX, nextY)) {
            setX(nextX);
            setY(nextY);
        }
    }

    //Getters og Setters
    public ImageView getImageView() {
        return imageView;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
        imageView.setTranslateX(x);
    }

    public void setY(double y) {
        this.y = y;
        imageView.setTranslateY(y);
    }

    protected void setPacman(PacMan pacman) {
        this.pacman = pacman;
    }

    protected boolean checkWallCollision(double nextX, double nextY) {
        return Kart.isWall(nextX, nextY);
    }
}

