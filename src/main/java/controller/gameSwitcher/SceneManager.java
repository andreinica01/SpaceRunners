package controller.gameSwitcher;

import java.io.IOException;
import controller.gameLoop.GameManager;
import controller.ranking.Ranking;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneManager {

	private Stage mainWindow;
	private GameManager gameManager;
	private MenuController menuController;
	private Ranking ranking;

	/**
	 * Constructor.
	 * 
	 * @param mainWindow
	 * @throws IOException
	 */
	public SceneManager(final Stage mainWindow) throws IOException {
		this.mainWindow = mainWindow;
		this.ranking = new Ranking();
		this.menuController = new MenuController(this);
		this.mainWindow.show();
	}

	/**
	 * Switch to Start Menu.
	 * 
	 * @throws IOException
	 */
	public void switchToStartMenu() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/StartMenu.fxml"));
	}

	/**
	 * Switch to Game.
	 * 
	 * @throws IOException
	 */
	public void switchToGame(final String name) throws IOException {
		this.gameManager = new GameManager(this);
		this.gameManager.setPlayerName(name);
		this.mainWindow.setScene(this.gameManager.getGameField().getGameContainer().getScene());
		this.gameManager.start();
	}

	/**
	 * Switch to Score Menu.
	 * 
	 * @throws IOException
	 */
	public void switchToScores() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/Scores.fxml"));
	}

	/**
	 * Switch to Name Menu.
	 * 
	 * @throws IOException
	 */
	public void switchToNickname() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/Nickname.fxml"));
	}

	/**
	 * Switch to End Game menu.
	 * 
	 * @throws IOException
	 */
	public void switchToEndMenu() throws IOException {
		this.gameManager.stop();
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/EndMenu.fxml"));
	}

	/**
	 * Switch to Controls Menu.
	 * 
	 * @throws IOException
	 */
	public void switchToControls() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/Controls.fxml"));
	}

	/**
	 * Quit from the game.
	 */
	public void quit() {
		Platform.exit();
		System.exit(0);
	}

	/**
	 * @param path
	 * @return a loaded scene from FXML file.
	 * @throws IOException
	 */
	private Scene getSceneFromFxml(final String path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
		loader.setController(this.menuController);
		return new Scene(loader.load());
	}

	/**
	 * @return Stage reference.
	 */
	public Stage getStage() {
		return this.mainWindow;
	}

	/**
	 * @return Ranking object reference.
	 */
	public Ranking getRanking() {
		return this.ranking;
	}

	/**
	 * Set score reference.
	 * 
	 * @param score
	 */
	public void setScore(final Text score) {
		this.menuController.setScore(score);
	}

	/**
	 * @return score reference.
	 */
	public Text getScore() {
		return this.menuController.getScore();
	}

	/**
	 * @return menuController reference.
	 */
	public MenuController getMenuController() {
		return this.menuController;
	}
}
