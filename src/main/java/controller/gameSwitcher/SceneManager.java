package controller.gameSwitcher;

import java.io.IOException;

import controller.gameEventController.SoundManager;
import controller.gameLoop.GameManager;
import controller.inputController.InputControllerImpl;
import controller.ranking.Ranking;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 */
public class SceneManager {

    private Stage mainWindow;
    private GameManager gameManager;
    private InputControllerImpl inputController;
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
        this.inputController = new InputControllerImpl(new Scene(new Group()));
        this.mainWindow.show();
        new SoundManager().playMusicMenu();
    }

    /**
     * Switch to Start Menu.
     * 
     * @throws IOException
     */
    public void switchToStartMenu() throws IOException {
        Scene scene = this.getSceneFromFxml("fxml/StartMenu.fxml", new StartMenuController(this));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
    }

    /**
     * Switch to Game.
     * 
     * @throws IOException
     */
    public void switchToGame(final String name) throws IOException {
        this.gameManager = new GameManager(this);
        this.gameManager.setPlayerName(name);
        Scene scene = this.gameManager.getGameField().getGameContainer().getScene();
        this.mainWindow.setScene(scene);
        this.inputController.changeScene(scene);
        this.gameManager.start();
    }

    /**
     * Switch to Score Menu.
     * 
     * @throws IOException
     */
    public void switchToScores() throws IOException {
        Scene scene = this.getSceneFromFxml("fxml/Scores.fxml", new ScoresController(this));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
        this.mainWindow.show();
    }

    /**
     * Switch to Name Menu.
     * 
     * @throws IOException
     */
    public void switchToNickname() throws IOException {
        Scene scene = this.getSceneFromFxml("fxml/Nickname.fxml", new NicknameController(this));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
    }

    /**
     * Switch to End Game menu.
     * @param scores 
     * 
     * @throws IOException
     */
    public void switchToEndMenu(final int scores) throws IOException {
        this.gameManager.stop();
        Scene scene = this.getSceneFromFxml("fxml/EndMenu.fxml", new EndGameController(this, scores));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
    }

    /**
     * Switch to Controls Menu.
     * 
     * @throws IOException
     */
    public void switchToControls() throws IOException {
        Scene scene = this.getSceneFromFxml("fxml/Controls.fxml", new ControlsController(this));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
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
    private Scene getSceneFromFxml(final String path, final BasicFXMLController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
        loader.setController(controller);
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
     * @return input controller reference.
     */
    public InputControllerImpl getInputController() {
        return this.inputController;
    }
}
