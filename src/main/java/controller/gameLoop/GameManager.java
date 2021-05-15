package controller.gameLoop;



import controller.gameController.GameContollerImpl;
import controller.gameController.GameController;
import javafx.animation.*;
import javafx.stage.Stage;
import view.gameField.GameFieldImpl;
import view.mainWindow.MainWindow;
import view.mainWindow.MainWindowImpl;

public class GameManager extends AnimationTimer {

    private MainWindow mainwindow;

    private GameFieldImpl gamefield;

    private GameController gameController;


    public GameManager(Stage stage)
    {

        this.mainwindow = new MainWindowImpl(stage);
        this.gamefield = new GameFieldImpl(1280,720);
        
        
        

        this.mainwindow.setWidth(1280);
        this.mainwindow.setHeight(720);
    
        this.mainwindow.addGameField(this.gamefield);
        this.gameController = new GameContollerImpl(this.gamefield);
         

        stage.show();
        //this.mainwindow.setVisible(true);
     

    }

 


    @Override
    public void handle(long arg0) {
        
        
        this.gameController.update();

       

        
        
        
    }
    
}
