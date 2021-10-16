package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NicknameController extends BasicFXMLController {
    
    private Integer maxNameSize;
    @FXML
    private TextField nickname;


    public NicknameController(Stage mainWindow, SceneManager sceneManager) throws IOException {
        super(mainWindow, sceneManager);
        this.maxNameSize = 15;
    }

    @FXML
    void nicknameEvent(ActionEvent event) throws IOException {
        String name = this.nickname.getText();
        if (!name.isBlank() && name.length() <= this.maxNameSize) {
            this.buttonPressedSound();
            this.getSceneManager().switchToGame(name);
        }
    }

    @FXML
    void showStartMenu(ActionEvent event) throws IOException {
        this.buttonPressedSound();
        this.getSceneManager().switchToStartMenu();
    }

}
