package view.mainWindow;

import javafx.stage.Stage;
import view.gameField.GameFieldImpl;

public interface MainWindow {

    /**
     * Add the gamefield to the stage and set its scene.
     * @param gameField
     */
    void addGameField(GameFieldImpl gameField);

    /**
     * @return actual Stage.
     */
    Stage getStage();
}
