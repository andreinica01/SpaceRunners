package controller.gameSwitcher;

import java.io.IOException;

import controller.gameEventController.SoundManager;
import controller.gameLoop.GameManager;
import controller.inputController.InputController;
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
public class SceneManagerImpl implements SceneManager{

    private Stage mainWindow;
    private GameManager gameManager;
    private InputController inputController;
    private Ranking ranking;

    /**
     * Constructor.
     * 
     * @param mainWindow
     * @throws IOException
     */
    public SceneManagerImpl(final Stage mainWindow) throws IOException {
        this.mainWindow = mainWindow;
        this.ranking = new Ranking();
        this.inputController = new InputControllerImpl(new Scene(new Group()));
        this.mainWindow.show();
        new SoundManager().playMusicMenu();
    }

    @Override
    public void switchToStartMenu() throws IOException {
        Scene scene = this.getSceneFromFxml("fxml/StartMenu.fxml", new StartMenuController(this));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
    }

    @Override
    public void switchToGame(final String name) throws IOException {
        this.gameManager = new GameManager(this);
        this.gameManager.setPlayerName(name);
        Scene scene = this.gameManager.getGameField().getGameContainer().getScene();
        this.mainWindow.setScene(scene);
        this.inputController.changeScene(scene);
        this.gameManager.start();
    }

    @Override
    public void switchToScores() throws IOException {
        Scene scene = this.getSceneFromFxml("fxml/Scores.fxml", new ScoresController(this));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
        this.mainWindow.show();
    }

    @Override
    public void switchToNickname() throws IOException {
        Scene scene = this.getSceneFromFxml("fxml/Nickname.fxml", new NicknameController(this));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
    }

    @Override
    public void switchToEndMenu(final int scores) throws IOException {
        this.gameManager.stop();
        Scene scene = this.getSceneFromFxml("fxml/EndMenu.fxml", new EndGameController(this, scores));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
    }

    @Override
    public void switchToControls() throws IOException {
        Scene scene = this.getSceneFromFxml("fxml/Controls.fxml", new ControlsController(this));
        this.inputController.changeScene(scene);
        this.mainWindow.setScene(scene);
    }

    @Override
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

    @Override
    public Stage getStage() {
        return this.mainWindow;
    }

    @Override
    public Ranking getRanking() {
        return this.ranking;
    }

    @Override
    public InputController getInputController() {
        return this.inputController;
    }
}
