package controller.gameSwitcher;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.SoundManager.SoundManager;

public class MenuController {

	private SceneManager sceneManager;
	private SoundManager soundManager;

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
		this.sceneManager.quit();
	}

	@FXML
	private void showScores(final ActionEvent event) {
		this.sceneManager.switchToScores();
	}

	@FXML
	private void startGame(final ActionEvent event) throws IOException {
		this.sceneManager.switchToGame();
	}
}
