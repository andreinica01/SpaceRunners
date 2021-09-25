package controller.gameSwitcher;

import java.io.IOException;
import controller.gameLoop.GameManager;
import javafx.application.Platform;
import javafx.stage.Stage;

public class SceneManager {

	private Stage mainWindow;
	private GameManager gameManager;

	public SceneManager(final Stage mainWindow) throws IOException {
		this.mainWindow = mainWindow;
	}

	public void switchToGame() throws IOException {
		this.gameManager = new GameManager(this.mainWindow);
		this.gameManager.start();
		System.out.println("Game Size");
		System.out.println(this.mainWindow.getScene().getHeight());
		System.out.println(this.mainWindow.getScene().getWidth());
	}
	
	public void switchToScores() {
		System.out.println("scores");
	}
	
	public void quit() {
		Platform.exit();
		System.exit(0);
	}
}
