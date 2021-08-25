package application;

import controller.gameLoop.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage mainwindow) {
    try {
      GameManager x = new GameManager(mainwindow);
      mainwindow.setTitle("SpaceInvaders");

      x.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
