package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * 
 */
public class StartMenuController extends BasicFXMLController {

    /**
     * Constructor.
     * @param mainWindow
     * @throws IOException
     */
    public StartMenuController(final SceneManager sceneManager) throws IOException {
        super(sceneManager);
    }

    /**
     * Show choose name GUI.
     * @param event
     * @throws IOException
     */
    @FXML
    void pickNickname(final ActionEvent event) throws IOException {
        super.buttonPressedSound();
        super.getSceneManager().switchToNickname();
    }

    /**
     * Ends the game.
     * @param event
     */
    @FXML
    void quit(final ActionEvent event) {
        super.buttonPressedSound();
        super.getSceneManager().quit();
    }

    /**
     * Show scores GUI.
     * @param event
     * @throws IOException
     */
    @FXML
    void showScores(final ActionEvent event) throws IOException {
        super.buttonPressedSound();
        super.getSceneManager().switchToScores();
    }

    /**
     * Show controls GUI.
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToControls(final ActionEvent event) throws IOException {
        super.buttonPressedSound();
        super.getSceneManager().switchToControls();
    }
}
