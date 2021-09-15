package Utilities;

import java.io.File;
import javafx.scene.image.Image;
import javax.sound.sampled.AudioInputStream;

public class Parameters {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 720;

    public static final int INITIAL_PLAYER_POINTS = 3;
    public static final int STATUS_SPEED = 6;

    public static String ResourcesFolder = "src/main/resources/";
    public static String ImageFolder = ResourcesFolder + "Images/";
    public static String SoundFolder = ResourcesFolder + "Sounds/";

    public static Image getlifePointsImage() {
        return new Image(new File(ImageFolder + "spaceshipLife.png").toURI().toString());
    }

    /* Images */
    public static Image bulletImage = new Image(new File(ImageFolder + "bullet.png").toURI().toString());
    public static Image BackgroundImage = new Image(new File(ImageFolder + "stars2.png").toURI().toString());
    public static Image playerImage = new Image(new File(ImageFolder + "playerShip.png").toURI().toString());
    public static Image enemyImage = new Image(new File(ImageFolder + "enemy.png").toURI().toString());
    public static Image enemyExplodingImage = new Image(new File(ImageFolder + "explosion.png").toURI().toString());
    public static Image lifePointsImage = new Image(new File(ImageFolder + "spaceshipLife.png").toURI().toString());
    public static Image bonusLifeImage = new Image(new File(ImageFolder + "bonus0.png").toURI().toString(), 50, 50,
            true, true);
    public static Image bonusSpeedImage = new Image(new File(ImageFolder + "bonus1.png").toURI().toString(), 50,
            50, true, true);
    public static Image malusCommandImage = new Image(new File(ImageFolder + "bonus2.png").toURI().toString(), 50,
            50, true, true);
    public static Image malusFireImage = new Image(new File(ImageFolder + "bonus3.png").toURI().toString(), 50, 50,
            true, true);
    public static Image malusSpeedImage = new Image(new File(ImageFolder + "bonus4.png").toURI().toString(), 50, 50,
            true, true);
    public static Image randomStatusImage = new Image(new File(ImageFolder + "randomStatus.png").toURI().toString(), 50,
            50, true, true);
    public static Image bossShipImage = new Image(new File(ImageFolder + "bossShip.png").toURI().toString());

    /* Sounds */
    public static AudioInputStream bulletSound;
}