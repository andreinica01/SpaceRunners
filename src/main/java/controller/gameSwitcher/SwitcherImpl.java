package controller.gameSwitcher;

import controller.gameLoop.GameManager;
import javafx.stage.Stage;

public class SwitcherImpl implements ISwitcher {

	private Stage gameManager;
	private GameManager gameStarter;
	
	public SwitcherImpl(final Stage mainWindow) {
		this.gameManager = mainWindow;

		this.gameStarter = new GameManager(this.gameManager);
		this.startGame();
	}
	
	@Override
	public final void startMenu() {
		// TODO Auto-generated method stub
	}

	@Override
	public final void startGame() {
		this.gameStarter.start();
	}

	@Override
	public final void endGame() {
		this.gameStarter.stop();
		//to do
	}
}
