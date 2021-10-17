package controller.gameSwitcher;

import java.io.IOException;
import javafx.stage.Stage;
import view.SoundManager.SoundManager;

/**
 *
 */
public abstract class BasicFXMLController {

    private Stage mainWindow;
    private SoundManager soundManager;
    private SceneManager sceneManager;

    /**
     * Constructor.
     * @param mainWindow
     * @param sceneManager
     * @throws IOException
     */
    public BasicFXMLController(final Stage mainWindow, final SceneManager sceneManager) throws IOException {
        this.mainWindow = mainWindow;
        this.sceneManager = sceneManager;
        this.soundManager = new SoundManager();
    }

    /**
     * Play a sound when a button is pressed.
     */
    public void buttonPressedSound() {
        this.soundManager.playButtonClicked();
    }

    /**
     * @return mainWindow reference.
     */
    public Stage getMainWindow() {
        return this.mainWindow;
    }

    /**
     * @return soundManager reference.
     */
    public SoundManager getSoundManager() {
        return this.soundManager;
    }

    /**
     * @return sceneManager reference.
     */
    public SceneManager getSceneManager() {
        return this.sceneManager;
    }
}
