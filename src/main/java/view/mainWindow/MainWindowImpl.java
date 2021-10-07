package view.mainWindow;

import controller.gameSwitcher.SceneManager;
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
    public MainWindowImpl(final SceneManager gameManager) {
        this.stage = gameManager.getStage();
        this.stage.setResizable(false);
    }

    @Override
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.stage.show();
            return;
        }
        this.stage.hide();
    }

    @Override
    public final void addGameField(final GameFieldImpl gameField) {
        Scene scene = new Scene(new VBox(gameField.getGameContainer()));

        gameField.setScene(scene);
        gameField.setStageReference(this.getStage());
        this.stage.setScene(scene);
    }

    @Override
    public final void setWidth(final Number width) {
        this.stage.setWidth(width.intValue());
    }

    @Override
    public final void setHeight(final Number heigth) {
        this.stage.setHeight(heigth.intValue());
    }

    @Override
    public final Stage getStage() {
        return this.stage;
    }
}
