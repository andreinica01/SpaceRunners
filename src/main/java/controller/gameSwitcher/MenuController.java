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
import utilities.VariousMagicNumbers;
import view.SoundManager.SoundManager;

public class MenuController {

	private SceneManager sceneManager;
	private InputControllerImpl inputController;
	private SoundManager soundManager;

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
	private KeyCode lastKeyPressed;
	@FXML
	private Text keyToSetText;

	/*
	 * Constructor.
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
		Text rankText = new Text(this.sceneManager.getRanking().toString());
		rankText.setFill(Color.GREEN);
		rankText.setFont(Font.font("Verdana", FontWeight.BOLD, VariousMagicNumbers.SIXTEEN));
		this.scoreText.getChildren().add(rankText);
		this.scoreText.setVisible(true);
		System.out.println(rankText.toString());
	}

	@FXML
	void pickNickname(final ActionEvent event) throws IOException {
		this.soundManager.playButtonClicked();
		this.sceneManager.switchToNickname();
		this.inputController.resetStates();
	}

	@FXML
	void showStartMenu(final ActionEvent event) throws IOException {
		this.soundManager.playButtonClicked();
		this.sceneManager.switchToStartMenu();
	}

	@FXML
	void switchToControls(final ActionEvent event) throws IOException {
		this.soundManager.playButtonClicked();
		this.sceneManager.switchToControls();
		this.controlsLeftText.setText(this.inputController.getKeysListByCommand(InputCommand.LEFT).get(0).toString());
		this.controlsRightText.setText(this.inputController.getKeysListByCommand(InputCommand.RIGHT).get(0).toString());
		this.controlsAttackText.setText(this.inputController.getKeysListByCommand(InputCommand.ATTACK).get(0).toString());
	}

	@FXML
	void nicknameEvent(final ActionEvent event) throws IOException {
		String name = this.nickname.getText();
		if (!name.isBlank() && name.length() <= 15) {
			this.soundManager.playButtonClicked();
			this.sceneManager.switchToGame(name);
		}
	}

	@FXML
	void startGame(final ActionEvent event) throws IOException {
		this.soundManager.playButtonClicked();
		this.sceneManager.switchToNickname();
	}

	@FXML
	void returnToMenu(final ActionEvent event) throws IOException {
		this.soundManager.playButtonClicked();
		this.sceneManager.switchToStartMenu();
	}

	/**
	 * Set score reference.
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
	void changeLeftKey(ActionEvent event) {
		this.inputController.addCommandKeys(lastKeyPressed, InputCommand.LEFT);
		this.controlsLeftText.setText(this.inputController.getMapGrouped().get(InputCommand.LEFT).get(0).toString());
	}

	@FXML
	void changeRightKey(ActionEvent event) {
		this.inputController.addCommandKeys(lastKeyPressed, InputCommand.RIGHT);
		this.controlsRightText.setText(this.inputController.getMapGrouped().get(InputCommand.RIGHT).get(0).toString());
	}

	@FXML
	void changeAttackKey(ActionEvent event) {
		this.inputController.addCommandKeys(this.lastKeyPressed, InputCommand.ATTACK);
		this.controlsAttackText.setText(this.inputController.getMapGrouped().get(InputCommand.ATTACK).get(0).toString());
	}

	@FXML
	void detectKey(KeyEvent event) {
		this.lastKeyPressed = event.getCode();
		this.keyToSetText.setText(event.getCode().toString());
	}

	public InputControllerImpl getInputController() {
		return this.inputController;
	}

}
