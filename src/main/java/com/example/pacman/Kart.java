package com.example.pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kart {
    public static ArrayList<String> kart;

    /**
     * Forsøker på å lese fil og legger inn hver char inn i ArrayList Kart
     * @return Returnerer kartet som array liste
     */
    public static ArrayList<String> lesKart(){
        try {
            File fil = new File("src/main/java/com/example/pacman/Kart.txt");
            Scanner scanner = new Scanner(fil);
            kart = new ArrayList<>();
            while (scanner.hasNext()) {
                String nice = scanner.next();
                kart.add(nice);
                System.out.println(nice);
            }
            scanner.close();
        }catch(FileNotFoundException e){
            System.out.println("ERROR! Fil ikke funnet - " + e);
        }
        return kart;
    }

    /**
     *
     * @param x X verdien som blir brukt for å sjekke om det er vegg
     * @param y Y verdien som blir brukt for å sjekke om det er vegg
     * @return Returnerer true om posisjonen er en vegg
     */
    public static boolean isWall(double x, double y) {
        int gridX = (int) (x / Spill.PIXEL);
        int gridY = (int) (y / Spill.PIXEL);

        if (gridX < 0 || gridY < 0 || gridX >= kart.get(0).length() || gridY >= kart.size())
            return false;

        return kart.get(gridY).charAt(gridX) == '#';
    }

}

