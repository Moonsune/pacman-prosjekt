package com.example.pacman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.Socket;
import java.util.ArrayList;

public class Spill extends Application {
    protected static Pane spillPane;
    private static Image pacmanImage = new Image("file:src/main/java/com/example/pacman/pacman.png");
    protected static PacMan pacman = new PacMan(120,100,pacmanImage);
    private static Image blinkyImage = new Image("file:src/main/java/com/example/pacman/blinky.png");
    protected static Blinky blinky = new Blinky(260,220,blinkyImage);
    private static Image inkyImage = new Image("file:src/main/java/com/example/pacman/inky.png");
    protected static Inky inky = new Inky(260,280,inkyImage);
    private static Image pinkyImage = new Image("file:src/main/java/com/example/pacman/pinky.png");
    protected static Pinky pinky = new Pinky(220,280,pinkyImage);
    private static Image clydeImage = new Image("file:src/main/java/com/example/pacman/clyde.png");
    protected static Clyde clyde = new Clyde(320, 300, clydeImage);
    protected static Text txt_score;
    private static AnimationTimer gameLoop;

    private static Text txt_liv;
    private static Text txt_gameover;
    protected static String poengString = "0";
    private static ArrayList<Spøkelse> spøkelser_AL = new ArrayList<>();

    public static final int PIXEL = 20;
    public static final int HØYDE = 650;
    public static final int BREDDE = 560;
    public static ArrayList<Vegg> veggArrayList = new ArrayList<>();
    public static ArrayList<PrikkPoeng> matArrayList = new ArrayList<>();
    protected static int antallLiv = 3;
    protected static int poengSum = 0;


    /**
     * Metode som grupperer funksjonene for å bevege spøkelser for å gjøre kode lesbar
     */
    private void moveGhosts() {

        blinky.beveg(); // Move Blinky
        pinky.beveg(); // Move Pinky
        clyde.beveg(); // Move Clyde
        inky.beveg();  // Move Inky

    }

    @Override
    public void start(Stage stage) throws Exception {
        spillPane = new Pane();
        Scene scene = new Scene(spillPane, BREDDE, HØYDE);
        scene.setFill(Color.BLACK);

        tegnKart(Kart.lesKart());
        spillPane.getChildren().add(pacman.getImageView());


        Text lbl_score = new Text(230, 15,"Score: ");
        lbl_score.setFill(Color.WHITE);
        txt_score = new Text(270, 15, poengString);
        txt_score.setFill(Color.WHITE);

        txt_gameover = new Text(400, 15, "");
        txt_gameover.setFill(Color.RED);

        txt_liv = new Text(100, 15, "Liv: " + antallLiv);
        txt_liv.setFill(Color.YELLOW);
        spillPane.getChildren().addAll(lbl_score,txt_score, txt_liv, txt_gameover);


        spøkelser_AL.add(blinky);
        spøkelser_AL.add(pinky);
        spøkelser_AL.add(inky);
        spøkelser_AL.add(clyde);


        pacman.getImageView().setFitWidth(PIXEL);
        pacman.getImageView().setFitHeight(PIXEL);

        for (Spøkelse s: spøkelser_AL
             ) {
            s.getImageView().setFitWidth(PIXEL);
            s.getImageView().setFitHeight(PIXEL);
            s.setPacman(pacman);
        }

        System.out.println("Initial positions and directions of the ghosts:");
        System.out.println("Blinky: X = " + blinky.getX() + ", Y = " + blinky.getY() + ", Direction = " + blinky.retning);
        System.out.println("Pinky: X = " + pinky.getX() + ", Y = " + pinky.getY() + ", Direction = " + pinky.retning);
        System.out.println("Inky: X = " + inky.getX() + ", Y = " + inky.getY() + ", Direction = " + inky.retning);
        System.out.println("Clyde: X = " + clyde.getX() + ", Y = " + clyde.getY() + ", Direction = " + clyde.retning);


        spillPane.getChildren().addAll(pinky.getImageView(), inky.getImageView(), clyde.getImageView(), blinky.getImageView());


        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;
            private final double moveInterval = 80_000_000L; // Move Clyde every second

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= moveInterval) {
                    pacman.beveg(); // Move Pac-Man
                    moveGhosts(); // Move ghosts
                    lastUpdate = now;
                    isHurt(pacman,spøkelser_AL);

                    }
                if (isGameover(antallLiv))
                    gameLoop.stop();
            }

        };
        gameLoop.start();

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
                pacman.flytt(keyCode);
            System.out.println("X: " + pacman.getX() + ", Y: " + pacman.getY());

        });

        stage.setScene(scene);
        stage.setTitle("Pac-Man");
        stage.show();
    }

    /**
     * Metode for å sjekke om antall liv er lavere enn null. returnerer true eller false.
     * @param antallLiv Henter int verdi som representerer liv
     */
    public static boolean isGameover(int antallLiv) {
        //gameOver();
        //Highscore.isHigher(poengSum);
        return antallLiv < 1;
    }

    /**
     * Metode for å tegne kartet inn i JavaFX applikasjonen
     * @param kart Tar i bruk Kart listen for å sjekke tegn
     */
    public static void tegnKart(ArrayList<String> kart) {
        Color farge = Color.BLUE;

        double x = 0, y = 0;
        for(int i = 0; i < kart.size(); i++) {

            for(int k = 0; k < kart.get(i).length(); k++) {

                switch(kart.get(i).charAt(k)) {
                    case '#' :  Vegg vegg = new Vegg(x,y);
                        veggArrayList.add(vegg);
                        Rectangle v = vegg.lagVegg(farge);
                        spillPane.getChildren().add(v);break;

                    case 'D' :  PrikkPoeng mat = new PrikkPoeng(x,y);
                        matArrayList.add(mat);
                        spillPane.getChildren().add(mat.posisjon); break;

                    case '*' :  PrikkBuff buff = new PrikkBuff(x,y);
                        spillPane.getChildren().add(buff.posisjon); break;

                    case 'S' :  pacman.getImageView().setTranslateX(x);
                        pacman.setX(x);
                        pacman.setY(y);
                        pacman.getImageView().setTranslateY(y);
                        break;
                    case 'G' :
                }
                x += PIXEL;
            }
            y += PIXEL;
            x = 0;
        }

    }

    /**
     * funksjon som registrerer kontakt mellom spøkelse og pacman for å fjerne antallliv. Denne metoden er fremdeles ikke ferdig implementert
     * @param pacman Parameter for spiller
     * @param spøkelser Parameter for spøkelse. Denne tar bare inn ett enkelt spøkelses objekt, men burde bli gjort om til en liste ved fremtidige oppdateringer
     */
    public static void isHurt(PacMan pacman, ArrayList<Spøkelse> spøkelser) {
        for(Spøkelse s: spøkelser
             ) {
            if ((pacman.getX() == s.getX() && pacman.getY() == s.getY())) {
                for (Spøkelse s1 : spøkelser)
                {
                    s1.setX(260);
                    s1.setY(300);
                }
                System.out.println("mistet liv");
                antallLiv--;
                txt_liv.setText("Liv: " + antallLiv);
                pacman.setX(260);
                pacman.setY(360);

            }
        }
        if(isGameover(antallLiv)) {
            System.out.println("spillet er over");
            txt_gameover.setText("Gameover");
        }

    }


    public static void main(String ...args) {
        launch(args);
    }
}
