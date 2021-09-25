package controller.gameSwitcher;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.scene.control.TextField;
=======
import view.SoundManager.SoundManager;
>>>>>>> ac92e73154a68f56995ab1e45ff280c8c43f320d

public class MenuController {

	private SceneManager sceneManager;
<<<<<<< HEAD
	
	@FXML 
	private TextField nickname;

	public MenuController(SceneManager sceneManager) {
=======
	private SoundManager soundManager;

	/*
	 * Constructor.
	 */
	public MenuController(final SceneManager sceneManager) {
>>>>>>> ac92e73154a68f56995ab1e45ff280c8c43f320d
		this.sceneManager = sceneManager;
		this.soundManager = new SoundManager();
		this.soundManager.playMusicMenu();
	}

	@FXML
	private void quit(final ActionEvent event) {
		this.sceneManager.quit();
	}

	@FXML
<<<<<<< HEAD
	void showScores(ActionEvent event) throws IOException {
=======
	private void showScores(final ActionEvent event) {
>>>>>>> ac92e73154a68f56995ab1e45ff280c8c43f320d
		this.sceneManager.switchToScores();
	}

	@FXML
<<<<<<< HEAD
	void pickNickname(ActionEvent event) throws IOException {
		this.sceneManager.switchToNickname();
	}

	@FXML
	void showStartMenu(ActionEvent event) throws IOException {
		this.sceneManager.switchToStartMenu();
	}
    
	@FXML
	void nicknameEvent(ActionEvent event) throws IOException {
	String name = this.nickname.getText();
	if (!name.isBlank()) {
		this.sceneManager.switchToGame(name);
		}
=======
	private void startGame(final ActionEvent event) throws IOException {
		this.sceneManager.switchToGame();
>>>>>>> ac92e73154a68f56995ab1e45ff280c8c43f320d
	}
}
