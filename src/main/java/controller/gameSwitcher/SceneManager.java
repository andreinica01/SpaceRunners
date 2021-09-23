package controller.gameSwitcher;

import java.io.IOException;

import controller.gameLoop.GameManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

	private Stage mainWindow;
	private GameManager gameManager;
	

	public SceneManager(final Stage mainWindow) throws IOException {
		this.mainWindow = mainWindow;
		this.gameManager = new GameManager(mainWindow);
	}

	public void switchToStartMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/StartMenu.fxml"));
		loader.setController(new MenuController(this.mainWindow));
		this.mainWindow.setScene(new Scene(loader.load()));
	}

	public void switchToGame() throws IOException {
		this.gameManager.start();
	}
}
