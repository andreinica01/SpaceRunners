package view.mainWindow;


import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.gameField.GameFieldImpl;

public class MainWindowImpl implements MainWindow {

    private Stage stage;
    private Scene mainScene;

    public MainWindowImpl(Stage original) {
        this.stage = original;
    }

    @Override
    public void setVisible(boolean visible) {

        if (visible)
            this.stage.show();

        this.stage.hide();
        this.stage.setResizable(false);

    }

    @Override
    public void addGameField(GameFieldImpl gamefield) {

        VBox vbox = new VBox(gamefield.getGameContainer());
        Scene scene = new Scene(vbox);
        this.mainScene = scene;

        gamefield.setScene(scene);
        this.stage.setScene(scene);
        this.stage.setResizable(false);

    }

    @Override
    public void setWidth(Number width) {

        this.stage.setWidth(width.intValue());



        
        
    

    }

    @Override
    public void setHeight(Number heigth) {
        this.stage.setHeight(heigth.intValue());

     

    }

    @Override
    public Scene getScene() {
        return this.mainScene;
    }



}
