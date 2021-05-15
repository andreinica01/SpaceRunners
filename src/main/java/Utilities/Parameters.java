package Utilities;

import java.io.File;

import javafx.scene.image.Image;

public interface Parameters {

    final int WIDTH = 1280;
    final int HEIGHT = 720;

    static String ImageFolder = "src/main/resources/Images/";
    static Image bulletImage = new Image(new File(ImageFolder+"bullet.png").toURI().toString());
    static Image BackgroundImage = new Image(new File(ImageFolder+"background.jpg").toURI().toString());
    


}
