package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndGameController extends BasicFXMLController {

    @FXML
    private Text score;
    
    private int scores;
    
    public EndGameController(Stage mainWindow, SceneManager sceneManager, int scores) throws IOException {
        super(mainWindow, sceneManager);
        this.scores = scores;
    }

    @FXML
    private void initialize() {
        this.score.setText(Integer.valueOf(this.scores).toString());
        this.score.setVisible(true);
    }

    @FXML
    void quit(ActionEvent event) {
        this.buttonPressedSound();
        this.getSceneManager().quit();
    }

    @FXML
    void returnToMenu(ActionEvent event) throws IOException {
        this.buttonPressedSound();
        this.getSceneManager().switchToStartMenu();
    }

}
