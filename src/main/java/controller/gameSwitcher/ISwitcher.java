package controller.gameSwitcher;

import java.io.IOException;

public interface ISwitcher {

	/**
	 * Starts the main menu.
	 * @throws IOException 
	 */
	void startMenu() throws IOException; 
	
	/**
	 * Starts the game.
	 */
	void startGame();
	
	/**
	 * Starts the end game menu.
	 * @throws IOException 
	 */
	void endGame() throws IOException;
}
