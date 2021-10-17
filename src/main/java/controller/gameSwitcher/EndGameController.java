package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 */
public class EndGameController extends BasicFXMLController {

    @FXML
    private Text score;

    private int scores;

    /**
     * Constructor.
     * @param mainWindow
     * @param sceneManager
     * @param scores
     * @throws IOException
     */
    public EndGameController(final Stage mainWindow, final SceneManager sceneManager,
                                final int scores) throws IOException {
        super(mainWindow, sceneManager);
        this.scores = scores;
    }

    /**
     * Initialize the GUI.
     */
    @FXML
    private void initialize() {
        this.score.setText(Integer.valueOf(this.scores).toString());
        this.score.setVisible(true);
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
     * Returns to main menu: game Cycle.
     * @param event
     * @throws IOException
     */
    @FXML
    void returnToMenu(final ActionEvent event) throws IOException {
        super.buttonPressedSound();
        super.getSceneManager().switchToStartMenu();
    }
}
