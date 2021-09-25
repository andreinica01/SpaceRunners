package controller.gameSwitcher;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.SoundManager.SoundManager;

public class MenuController {

	private SceneManager sceneManager;
	private SoundManager soundManager;

	@FXML
	private TextField nickname;

	/*
	 * Constructor.
	 */
	public MenuController(final SceneManager sceneManager) {
		this.sceneManager = sceneManager;
		this.soundManager = new SoundManager();
		this.soundManager.playMusicMenu();
	}

	@FXML
	private void quit(final ActionEvent event) {
		this.soundManager.playButtonClicked();
		this.sceneManager.quit();
	}

	@FXML
	private void showScores(final ActionEvent event) {
		this.soundManager.playButtonClicked();
		this.sceneManager.switchToScores();
	}

	@FXML
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
	}
	
	@FXML
	void startGame(final ActionEvent event) throws IOException {
		this.soundManager.playButtonClicked();
		this.sceneManager.switchToNickname();
	}
}
