package com.example.pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Highscore {

    public static int highScore;

    /**
     * Metode som returnerer tidligere highscore fra tekstfilen Highscore.txt
     * @return returnerer highscoren med datatypen int
     */
    public static int getHighscore(){
        try {
            File fil = new File("src/main/java/com/example/pacman/Highscore.txt");
            Scanner scanner = new Scanner(fil);
            highScore = 0;
            while (scanner.hasNext()) {
                highScore = Integer.parseInt(scanner.next());
            }
            scanner.close();
        }catch(FileNotFoundException e){
            System.out.println("ERROR! Fil ikke funnet - " + e);
        }
        return highScore;
    }


    /**
     * Metode som sjekker om ny score er høyere enn forrige highscore
     * @param nyScore parameter for den nye scoren
     */
    public static void isHigher(int nyScore) {
        try {
            int old_HighScore = getHighscore();

            // Sjekk om lengden på nyScore er større enn gammel score
            if( old_HighScore < nyScore) {
                highScore = nyScore;
                //Skriver ny info til Highscore.txt
                File fil = new File("src/main/java/com/example/pacman/Highscore.txt");
                PrintWriter skriver = new PrintWriter(fil);
                skriver.println(highScore);
                skriver.close();
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}