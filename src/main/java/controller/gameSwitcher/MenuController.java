package controller.gameSwitcher;

import java.io.IOException;

import controller.inputController.InputControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import utilities.InputCommand;
import utilities.MagicEnumDouble;
import view.SoundManager.SoundManager;

public class MenuController {

    private static final int FIFTEEN = 15;

    private SceneManager sceneManager;
    private InputControllerImpl inputController;
    private SoundManager soundManager;
    private KeyCode lastKeyPressed;

    @FXML
    private TextField nickname;
    @FXML
    private TextFlow scoreText;
    @FXML
    private Text score;
    @FXML
    private Text controlsLeftText;
    @FXML
    private Text controlsRightText;
    @FXML
    private Text controlsAttackText;
    @FXML
    private Text keyToSetText;

    /**
     * Constructor.
     * 
     * @param sceneManager
     */
    public MenuController(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.soundManager = new SoundManager();
        this.soundManager.playMusicMenu();
        this.inputController = new InputControllerImpl(new Scene(new Group()));
        this.lastKeyPressed = KeyCode.P;
    }

    @FXML
    private void quit(final ActionEvent event) {
        this.soundManager.playButtonClicked();
        this.sceneManager.quit();
    }

    @FXML
    private void showScores(final ActionEvent event) throws IOException {
        this.soundManager.playButtonClicked();
        this.sceneManager.switchToScores();
        Text rankText = new Text(this.sceneManager.getRanking().getFormattedRankingMap());
        rankText.setFill(Color.GREEN);
        rankText.setFont(Font.font("Verdana", FontWeight.BOLD, MagicEnumDouble.SIXTEEN.getValue()));
        this.scoreText.getChildren().add(rankText);
        this.scoreText.setVisible(true);
        System.out.println(rankText.toString());
    }

    @FXML
    private void pickNickname(final ActionEvent event) throws IOException {
        this.soundManager.playButtonClicked();
        this.sceneManager.switchToNickname();
        this.inputController.resetStates();
    }

    @FXML
    private void showStartMenu(final ActionEvent event) throws IOException {
        this.soundManager.playButtonClicked();
        this.sceneManager.switchToStartMenu();
    }

    @FXML
    private void switchToControls(final ActionEvent event) throws IOException {
        this.soundManager.playButtonClicked();
        this.sceneManager.switchToControls();
        this.controlsLeftText.setText(this.inputController.getCommandKeys().get(InputCommand.LEFT).toString());
        this.controlsRightText.setText(this.inputController.getCommandKeys().get(InputCommand.RIGHT).toString());
        this.controlsAttackText.setText(this.inputController.getCommandKeys().get(InputCommand.ATTACK).toString());
    }

    @FXML
    private void nicknameEvent(final ActionEvent event) throws IOException {
        String name = this.nickname.getText();
        if (!name.isBlank() && name.length() <= FIFTEEN) {
            this.soundManager.playButtonClicked();
            this.sceneManager.switchToGame(name);
        }
    }

    @FXML
    private void startGame(final ActionEvent event) throws IOException {
        this.soundManager.playButtonClicked();
        this.sceneManager.switchToNickname();
    }

    @FXML
    private void returnToMenu(final ActionEvent event) throws IOException {
        this.soundManager.playButtonClicked();
        this.sceneManager.switchToStartMenu();
    }

    /**
     * Set score reference.
     * 
     * @param score
     */
    public void setScore(final Text score) {
        this.score = score;
    }

    /**
     * @return score reference.
     */
    public Text getScore() {
        return this.score;
    }

    @FXML
    private void changeLeftKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommand.LEFT);
        this.refreshControlsKeyTest();
    }

    @FXML
    private void changeRightKey(final ActionEvent event) {
        this.inputController.addCommandKeys(lastKeyPressed, InputCommand.RIGHT);
        this.refreshControlsKeyTest();
    }

    @FXML
    private void changeAttackKey(final ActionEvent event) {
        this.inputController.addCommandKeys(this.lastKeyPressed, InputCommand.ATTACK);
        this.refreshControlsKeyTest();
    }

    private void refreshControlsKeyTest() {
        for (InputCommand comm : InputCommand.values()) {
            switch (comm) {
            case LEFT:
                this.controlsLeftText.setText(this.inputController.getCommandKeys().get(InputCommand.LEFT).toString());
                break;
            case RIGHT:
                this.controlsRightText
                        .setText(this.inputController.getCommandKeys().get(InputCommand.RIGHT).toString());
                break;
            case ATTACK:
                this.controlsAttackText
                        .setText(this.inputController.getCommandKeys().get(InputCommand.ATTACK).toString());
                break;

            default:
                break;
            }
        }
    }

    @FXML
    private void detectKey(final KeyEvent event) {
        this.lastKeyPressed = event.getCode();
        this.keyToSetText.setText(event.getCode().toString());
    }

    /**
     * @return InputController reference.
     */
    public InputControllerImpl getInputController() {
        return this.inputController;
    }
}
