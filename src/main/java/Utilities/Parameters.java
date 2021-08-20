package Utilities;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javafx.scene.image.Image;

public class Parameters {

    public Parameters()
    {
        try {
            bulletSound = AudioSystem.getAudioInputStream(new File(Parameters.SoundFolder+"laser.wav"));
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }
    
    public static final int WIDTH = 500;
    public static final int HEIGHT = 720;

    public static final int INITIAL_PLAYER_POINTS = 10;

    public static String ResourcesFolder = "src/main/resources/";
    public static String ImageFolder = ResourcesFolder+"Images/";
    public static String SoundFolder = ResourcesFolder+"Sounds/";

    /*Images*/
    public static Image bulletImage = new Image(new File(ImageFolder+"bullet.png").toURI().toString());
    public static Image BackgroundImage = new Image(new File(ImageFolder+"stars2.png").toURI().toString());
    public static Image playerImage = new Image(new File(ImageFolder+"spaceship2.png").toURI().toString());
    public static Image enemyImage = new Image(new File(ImageFolder+"enemyship.png").toURI().toString());
    public static Image bonusSpeedImage = new Image(new File(ImageFolder+"BonusSpeed.png").toURI().toString(),40,40,false,false);
    public static Image lifePointsImage = new Image(new File(ImageFolder+"spaceshipLife.png").toURI().toString());
    /*Sounds */
    public static AudioInputStream bulletSound ;
  

}
