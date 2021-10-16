package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class StartMenuController extends BasicFXMLController {

    public StartMenuController(final Stage mainWindow, final SceneManager sceneManager) throws IOException {
        super(mainWindow, sceneManager);
    }
    
    @FXML
    void pickNickname(ActionEvent event) throws IOException {
        this.buttonPressedSound();
        this.getSceneManager().switchToNickname();
    }

    @FXML
    void quit(ActionEvent event) {
        this.buttonPressedSound();
        this.getSceneManager().quit();
    }

    @FXML
    void showScores(ActionEvent event) throws IOException {
        this.buttonPressedSound();
        this.getSceneManager().switchToScores();
    }

    @FXML
    void switchToControls(ActionEvent event) throws IOException {
        this.buttonPressedSound();
        this.getSceneManager().switchToControls();
    }

}
