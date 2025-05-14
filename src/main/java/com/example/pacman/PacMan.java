package com.example.pacman;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class PacMan extends Spøkelse {
    private double dx = 0;
    private double dy = 0;

    public PacMan(double x, double y, Image image) {
        super(x, y, Color.YELLOW, image);
    }

    /**
     * Metode som flytter på pacman basert på pil taster.
     * @param tast Tar inn hvilken tast som blir trykket på.
     */
    public void flytt(KeyCode tast) {
        double newDx = dx;
        double newDy = dy;
        switch (tast) {
            case UP:
                newDx = 0;
                newDy = -1;
                imageView.setRotate(90);
                break;
            case DOWN:
                newDx = 0;
                newDy = 1;
                imageView.setRotate(270);
                break;
            case LEFT:
                newDx = -1;
                newDy = 0;
                imageView.setRotate(0);
                break;
            case RIGHT:
                newDx = 1;
                newDy = 0;
                imageView.setRotate(180);
                break;
        }

        if (newDx != dx || newDy != dy) {
            dx = newDx;
            dy = newDy;
        }
    }

    @Override
    public void beveg() {
        double nextX = getX() + dx * Spill.PIXEL;
        double nextY = getY() + dy * Spill.PIXEL;
        PrikkPoeng.spis(this.getX(), this.getY());

        if (nextX >= 0 && nextX < Spill.BREDDE && nextY >= 0 && nextY < Spill.HØYDE) {
            if (!checkWallCollision(nextX, nextY)) {
                setX(nextX);
                setY(nextY);
            }
        }
    }

}
