package view.mainWindow;

import Utilities.HUDParameters;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.gameField.GameFieldImpl;

public class MainWindowImpl implements MainWindow {

    private Stage stage;

    /**
     * Constructor.
     * @param original stage.
     */
    public MainWindowImpl(final Stage original) {
        this.stage = original;
        this.stage.setResizable(HUDParameters.FALSE);
    }

    @Override
    public void setVisible(final boolean visible) {
        if (visible) {
            this.stage.show();
            return;
        }

        this.stage.hide();
    }

    @Override
    public void addGameField(final GameFieldImpl gameField) {
        Scene scene = new Scene(new VBox(gameField.getGameContainer()));

        gameField.setScene(scene);
        gameField.setStageReference(this.getStage());
        this.stage.setScene(scene);
        this.stage.setResizable(HUDParameters.FALSE);
    }

    @Override
    public void setWidth(final Number width) {
        this.stage.setWidth(width.intValue());
    }

    @Override
    public void setHeight(final Number heigth) {
        this.stage.setHeight(heigth.intValue());
    }
    
    public Stage getStage() {
        return this.stage;
    }
}