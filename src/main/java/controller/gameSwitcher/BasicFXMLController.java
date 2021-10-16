package controller.gameSwitcher;

import java.io.IOException;

import javafx.stage.Stage;
import view.SoundManager.SoundManager;

public abstract class BasicFXMLController {
    
    private Stage mainWindow;
    private SoundManager soundManager;
    private SceneManager sceneManager;

     public BasicFXMLController(final Stage mainWindow, final SceneManager sceneManager) throws IOException {
         this.mainWindow = mainWindow;
         this.sceneManager = sceneManager;
         this.soundManager = new SoundManager();
     }
     
    public void buttonPressedSound() {
        this.soundManager.playButtonClicked();
    }

    public Stage getMainWindow() {
        return mainWindow;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
