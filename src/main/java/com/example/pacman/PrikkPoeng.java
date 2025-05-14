package com.example.pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import static com.example.pacman.Spill.*;

public class PrikkPoeng extends Objekter{
    protected Circle posisjon;

    /**
     * Konstruktør for PrikkPoeng altså maten som kun gir poeng når den blir spist. Blir brukt for å lage nytt PrikkPoeng Objekt og lager en Circle property for den.
     * @param x
     * @param y
     */
    public PrikkPoeng(double x, double y){
        super(x, y);
        boks = lagBoks(x, y);
        posisjon = new Circle(x+10, y+10, 5.0, Color.WHITE);
    }


    /**
     * Metode som bruker for-each løkke til å sjekke posisjon, fjerne mat, og legge til poeng
     * @param x x-verdien til pacman
     * @param y y-verdien til pacman
     */
    public static void spis(double x, double y) {
        ArrayList<PrikkPoeng> hjelpeListe = new ArrayList<>(matArrayList);
        try {
            for( PrikkPoeng mat : hjelpeListe) {
                if(mat.x == x && mat.y == y) {
                    Spill.spillPane.getChildren().remove(mat.posisjon);
                    matArrayList.remove(mat);

                    //Oppdatere score
                    poengSum += 100;
                    poengString = "" + poengSum;
                    txt_score.setText(poengString);
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}