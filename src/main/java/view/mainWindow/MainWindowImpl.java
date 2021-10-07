package view.mainWindow;

import controller.gameSwitcher.SceneManager;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.gameField.GameFieldImpl;

public class MainWindowImpl implements MainWindow {

    private Stage mainStage;

    /**
     * Constructor.
     * @param original stage.
     */
    public MainWindowImpl(final SceneManager gameManager) {
        this.mainStage = gameManager.getStage();
        this.mainStage.setResizable(false);
    }


    @Override
    public final void addGameField(final GameFieldImpl gameField) {
        Scene scene = new Scene(new VBox(gameField.getGameContainer()));
        gameField.setScene(scene);
        gameField.setStageReference(this.getStage());
    }

    @Override
    public final Stage getStage() {
        return this.mainStage;
    }



}
