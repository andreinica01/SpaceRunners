package controller.gameSwitcher;

import java.io.IOException;

import controller.inputController.InputControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import utilities.InputCommand;

/**
 *
 */
public class ControlsController extends BasicFXMLController {

    @FXML
    private Text keyToSetText;

    @FXML
    private Text controlsLeftText;

    @FXML
    private Text controlsRightText;

    @FXML
    private Text controlsAttackText;

    private InputControllerImpl inputController;
    private KeyCode lastKeyPressed;
 
    /**
     * Constructor.
     * @param sceneManager
     * @throws IOException
     */
    public ControlsController(final SceneManager sceneManager) throws IOException {
        super(sceneManager);
    }

    /**
     * Initialize the GUI.
     */
    @FXML
    private void initialize() {
        this.inputController = super.getSceneManager().getInputController();
        this.keyToSetText.setText(KeyCode.A.toString());
        this.refreshControlsText();
    }

    /**
     * Shows the chosen control on the GUI.
     */
    private void refreshControlsText() {
        this.controlsLeftText.setText(this.inputController.getCommandKeys().get(InputCommand.LEFT).toString());
        this.controlsRightText.setText(this.inputController.getCommandKeys().get(InputCommand.RIGHT).toString());
        this.controlsAttackText.setText(this.inputController.getCommandKeys().get(InputCommand.ATTACK).toString());
    }

    /**
     * Changes the attack key.
     * @param event
     */
    @FXML
    void changeAttackKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommand.ATTACK);
        this.refreshControlsText();
    }

    /**
     * Changes the left movement key.
     * @param event
     */
    @FXML
    void changeLeftKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommand.LEFT);
        this.refreshControlsText();
    }

    /**
     * Changes the right movement key.
     * @param event
     */
    @FXML
    void changeRightKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommand.RIGHT);
        this.refreshControlsText();
    }

    /**
     * Detects the key pressed to be set as new.
     * @param event
     */
    @FXML
    void detectKey(final KeyEvent event) {
        this.lastKeyPressed = event.getCode();
        this.keyToSetText.setText(event.getCode().toString());
    }

    /**
     * Returns to main menu GUI.
     * @param event
     * @throws IOException
     */
    @FXML
    void returnToMenu(final ActionEvent event) throws IOException {
        super.buttonPressedSound();
        super.getSceneManager().switchToStartMenu();
    }
}
