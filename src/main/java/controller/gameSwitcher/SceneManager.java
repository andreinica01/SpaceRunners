package controller.gameSwitcher;

import java.io.IOException;
import controller.gameLoop.GameManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

	private Stage mainWindow;
	private GameManager gameManager;
	private MenuController menuController;

	public SceneManager(final Stage mainWindow) throws IOException {
		this.mainWindow = mainWindow;
		this.menuController = new MenuController(this);
	}

	public void switchToStartMenu() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/StartMenu.fxml"));
	}

	public void switchToGame(String name) throws IOException {
		this.gameManager = new GameManager(this.mainWindow);
		this.gameManager.start();
	}
	
	public void switchToScores() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/Scores.fxml"));
	}

	public void switchToNickname() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/Nickname.fxml"));
	}
	
	public void quit() {
		Platform.exit();
		System.exit(0);
	}
	
	private Scene getSceneFromFxml (String path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
		loader.setController(this.menuController);
		return new Scene(loader.load());
	}



}
