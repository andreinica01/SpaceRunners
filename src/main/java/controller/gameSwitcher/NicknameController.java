package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 */
public class NicknameController extends BasicFXMLController {

    private static final int FIFTEEN = 15;

    private Integer maxNameSize;
    @FXML
    private TextField nickname;

    /**
     * Constructor.
     * @param mainWindow
     * @param sceneManager
     * @throws IOException
     */
    public NicknameController(final Stage mainWindow, final SceneManager sceneManager) throws IOException {
        super(mainWindow, sceneManager);
        this.maxNameSize = FIFTEEN;
    }

    /**
     * Checks the nickname insertion and save it.
     * @param event
     * @throws IOException
     */
    @FXML
    void nicknameEvent(final ActionEvent event) throws IOException {
        String name = this.nickname.getText();
        if (!name.isBlank() && name.length() <= this.maxNameSize) {
            super.buttonPressedSound();
            super.getSceneManager().switchToGame(name);
        }
    }

    /**
     * Returns to the starting menu of the game.
     * @param event
     * @throws IOException
     */
    @FXML
    void showStartMenu(final ActionEvent event) throws IOException {
        super.buttonPressedSound();
        super.getSceneManager().switchToStartMenu();
    }
}
