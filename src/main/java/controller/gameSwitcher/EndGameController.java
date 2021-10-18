package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 *
 */
public class EndGameController extends BasicFXMLController {

    @FXML
    private Text score;

    private int scores;

    /**
     * Constructor.
     * 
     * @param sceneManager
     * @param scores
     * @throws IOException
     */
    public EndGameController(final SceneManager sceneManager, final int scores) throws IOException {
        super(sceneManager);
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
     * 
     * @param event
     */
    @FXML
    void quit(final ActionEvent event) {
        super.buttonPressedSound();
        super.getSceneManager().quit();
    }

    /**
     * Returns to main menu: game Cycle.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void returnToMenu(final ActionEvent event) throws IOException {
        super.buttonPressedSound();
        super.getSceneManager().switchToStartMenu();
    }
}
