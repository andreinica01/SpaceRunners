package controller.gameLoop;

import Utilities.Parameters;
import controller.gameController.GameContollerImpl;
import controller.gameController.GameController;
import javafx.animation.*;
import javafx.stage.Stage;
import view.gameField.GameFieldImpl;
import view.mainWindow.MainWindow;
import view.mainWindow.MainWindowImpl;

public class GameManager extends AnimationTimer {

  long delta;
  long prevtime;
  private long sleepNs = 10000000;

  private MainWindow mainwindow;

  private GameFieldImpl gamefield;

  private GameController gameController;

  public GameManager(Stage stage) {
    this.mainwindow = new MainWindowImpl(stage);
    this.gamefield = new GameFieldImpl(Parameters.WIDTH, Parameters.HEIGHT);

    this.mainwindow.setWidth(Parameters.WIDTH);
    this.mainwindow.setHeight(Parameters.HEIGHT);

    this.mainwindow.addGameField(this.gamefield);
    this.gameController = new GameContollerImpl(this.gamefield);

    this.mainwindow.setVisible(true);
  }

  public double getFrameRateHertz(long delta) {
    double frameRate = 1d / delta;
    return frameRate * 1e9;
  }

  @Override
  public void handle(long now) {
    /* if ((now - prevtime) < sleepNs) {
            ("exit");
            return;
        } */

    try {
      Thread.sleep(0, 100000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    this.gameController.update();
    prevtime = now;
  }

  /**
   * @return time sleep value
   */
  public long getTimeSleep() {
    return this.sleepNs;
  }
}
