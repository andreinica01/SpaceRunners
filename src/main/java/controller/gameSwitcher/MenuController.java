package controller.gameSwitcher;

import controller.gameLoop.GameManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MenuController {

	private Stage mainWindow;


	public MenuController(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	@FXML
	void quit(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	void showScores(ActionEvent event) {
		System.out.println("scores");
	}

	@FXML
	void startGame(ActionEvent event) {
		new GameManager(this.mainWindow).start();
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}

}
