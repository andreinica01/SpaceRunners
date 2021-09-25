package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MenuController {

	private SceneManager sceneManager;
	
	@FXML 
	private TextField nickname;

	public MenuController(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	@FXML
	void quit(ActionEvent event) {
		this.sceneManager.quit();
	}

	@FXML
	void showScores(ActionEvent event) throws IOException {
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

}
