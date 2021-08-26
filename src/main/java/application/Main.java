package application;

import controller.gameLoop.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage mainWindow) {
        try {
            GameManager gameManager = new GameManager(mainWindow);
            mainWindow.setTitle("SpaceRunners");

            gameManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        launch(args);
    }
}