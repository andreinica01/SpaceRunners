package MainWindow;

import GameField.GameFieldImpl;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    }

    @Override
    public void addGameField(GameFieldImpl gamefield) {

        VBox vbox = new VBox(gamefield.getGameContainer());
        Scene scene = new Scene(vbox);
        this.mainScene = scene;

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

    @Override
    public Scene getScene() {

        return this.mainScene;
    }

}
