package controller.gameSwitcher;

import java.io.IOException;
import controller.gameLoop.GameManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.menu.EndGameGUI;

public class SwitcherImpl implements ISwitcher {

	private Stage gameManager;
	private GameManager gameStarter;
	
	public SwitcherImpl(final Stage mainWindow) {
		this.gameManager = mainWindow;

		this.gameStarter = new GameManager(this.gameManager);
		this.startGame();
	}
	
	@Override
	public final void startMenu() throws IOException {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("prova.fxml"));
		Scene scene = new Scene(root);
		this.gameManager.setScene(scene);
	}

	@Override
	public final void startGame() {
		this.gameStarter.start();
	}

	@Override
	public final void endGame() {
		this.gameStarter.stop();
		new EndGameGUI();
	}
}
