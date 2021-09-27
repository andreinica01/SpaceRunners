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

	public SceneManager(final Stage mainWindow) throws IOException, ClassNotFoundException {
		this.mainWindow = mainWindow;
		this.ranking = new Ranking();
		this.menuController = new MenuController(this);
	}

	public void switchToStartMenu() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/StartMenu.fxml"));
		this.mainWindow.show();
	}

	public void switchToGame(String name) throws IOException {
		this.gameManager = new GameManager(this);
		this.gameManager.setPlayerName(name);
		this.gameManager.start();
	}
	
	public void switchToScores() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/Scores.fxml"));
	}

	public void switchToNickname() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/Nickname.fxml"));
	}
	
	public void switchToEndMenu() throws IOException {
		this.mainWindow.setScene(this.getSceneFromFxml("fxml/EndMenu.fxml"));
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
	
	public Stage getStage() {
		return this.mainWindow;
	}

	public Ranking getRanking() {
		return this.ranking;
	}
	
	/**
	 * Set score reference
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
}
