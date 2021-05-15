package controller.gameLoop;



import controller.gameController.GameContollerImpl;
import controller.gameController.GameController;
import javafx.animation.*;
import javafx.stage.Stage;
import view.gameField.GameFieldImpl;
import view.mainWindow.MainWindow;
import view.mainWindow.MainWindowImpl;

import Utilities.Parameters;

public class GameManager extends AnimationTimer {

    private MainWindow mainwindow;

    private GameFieldImpl gamefield;

    private GameController gameController;


    public GameManager(Stage stage)
    {

        this.mainwindow = new MainWindowImpl(stage);
        this.gamefield = new GameFieldImpl(Parameters.WIDTH,Parameters.HEIGHT);
        
        this.mainwindow.setWidth(Parameters.WIDTH);
        this.mainwindow.setHeight(Parameters.HEIGHT);
    
        this.mainwindow.addGameField(this.gamefield);
        this.gameController = new GameContollerImpl(this.gamefield);
         
        
        this.mainwindow.setVisible(true);
     

    }

 


    @Override
    public void handle(long arg0) {
        
        
        this.gameController.update();

       

        
        
        
    }
    
}
