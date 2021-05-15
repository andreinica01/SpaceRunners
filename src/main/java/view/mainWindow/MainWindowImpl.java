package view.mainWindow;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.gameField.GameFieldImpl;

public class MainWindowImpl implements MainWindow {

    private Stage stage;

    public MainWindowImpl(Stage original) {
        this.stage = original;
        this.stage.setResizable(false);
    }

    @Override
    public void setVisible(boolean visible) {

        if (visible) {
            this.stage.show();
            return;
        }

        this.stage.hide();

    }

    @Override
    public void addGameField(GameFieldImpl gamefield) {

        Scene scene = new Scene(new VBox(gamefield.getGameContainer()));

        gamefield.setScene(scene);
        this.stage.setScene(scene);

    }

    @Override
    public void setWidth(Number width) {

        this.stage.setWidth(width.intValue());

    }

    @Override
    public void setHeight(Number heigth) {
        this.stage.setHeight(heigth.intValue());

    }

}
