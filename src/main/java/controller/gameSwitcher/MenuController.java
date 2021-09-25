package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

	private SceneManager sceneManager;

	
	public MenuController(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	@FXML
	void quit(ActionEvent event) {
		this.sceneManager.quit();
	}

	@FXML
	void showScores(ActionEvent event) {
		this.sceneManager.switchToScores();
	}

	@FXML
	void startGame(ActionEvent event) throws IOException {
		this.sceneManager.switchToGame();
	}

}
