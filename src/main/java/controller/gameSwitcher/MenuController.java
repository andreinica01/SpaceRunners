package controller.gameSwitcher;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

	private SceneManager sceneManager;

	/*
	 * Constructor.
	 */
	public MenuController(final SceneManager sceneManager) {
		this.sceneManager = sceneManager;
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
