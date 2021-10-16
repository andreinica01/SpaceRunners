package controller.gameSwitcher;

import java.io.IOException;

import controller.inputController.InputControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import utilities.InputCommand;

public class ControlsController extends BasicFXMLController {

    private InputControllerImpl inputController;
    
    @FXML
    private Text keyToSetText;
    
    @FXML
    private Text controlsLeftText;
    
    @FXML
    private Text controlsRightText;
    
    @FXML
    private Text controlsAttackText;
    
    private KeyCode lastKeyPressed;
 

    public ControlsController(Stage mainWindow, SceneManager sceneManager) throws IOException {
        super(mainWindow, sceneManager);
    }
    
    @FXML
    private void initialize() {
        this.inputController = this.getSceneManager().getInputController();
        this.keyToSetText.setText(KeyCode.A.toString());
        this.refreshControlsText();
    }

    private void refreshControlsText() {
        this.controlsLeftText.setText(this.inputController.getCommandKeys().get(InputCommand.LEFT).toString());
        this.controlsRightText.setText(this.inputController.getCommandKeys().get(InputCommand.RIGHT).toString());
        this.controlsAttackText.setText(this.inputController.getCommandKeys().get(InputCommand.ATTACK).toString());
    }

    @FXML
    void changeAttackKey(ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommand.ATTACK);
        this.refreshControlsText();
    }

    @FXML
    void changeLeftKey(ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommand.LEFT);
        this.refreshControlsText();
    }

    @FXML
    void changeRightKey(ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommand.RIGHT);
        this.refreshControlsText();
    }

    @FXML
    void detectKey(KeyEvent event) {
        this.lastKeyPressed = event.getCode();
        this.keyToSetText.setText(event.getCode().toString());
    }

    @FXML
    void returnToMenu(ActionEvent event) throws IOException {
        this.buttonPressedSound();
        this.getSceneManager().switchToStartMenu();
    }
}
