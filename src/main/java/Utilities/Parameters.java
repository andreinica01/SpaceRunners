package Utilities;

import java.io.File;

import javafx.scene.image.Image;

public interface Parameters {
    static String ImageFolder = "src/main/resources/Images/";
    static Image bulletImage = new Image(new File(ImageFolder+"bullet.png").toURI().toString());
}
