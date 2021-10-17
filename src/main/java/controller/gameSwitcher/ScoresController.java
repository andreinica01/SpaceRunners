package controller.gameSwitcher;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import utilities.MagicEnumDouble;

/**
 *
 */
public class ScoresController extends BasicFXMLController {

    @FXML
    private TextFlow scoreText;

    /**
     * Constructor.
     * @param mainWindow
     * @param sceneManager
     * @throws IOException
     */
    public ScoresController(final Stage mainWindow, final SceneManager sceneManager) throws IOException {
        super(mainWindow, sceneManager);
    }

    /**
     * Initialize the GUI.
     */
    @FXML
    private void initialize() {
        this.setUpScoresScene();
    }

    /**
     * Displays the scores.
     */
    private void setUpScoresScene() {
        this.refreshScoresInfo();
        this.scoreText.setVisible(true);
    }

    /**
     * Picking scores from file.
     */
    public void refreshScoresInfo() {
        Text rankText = new Text(this.getSceneManager().getRanking().getFormattedRankingMap());
        rankText.setFill(Color.GREEN);
        rankText.setFont(Font.font("Verdana", FontWeight.BOLD, MagicEnumDouble.SIXTEEN.getValue()));
        this.scoreText.getChildren().add(rankText);
    }

    /**
     * Shows the starting menu of the game.
     * @param event
     * @throws IOException
     */
    @FXML
    void showStartMenu(final ActionEvent event) throws IOException {
        super.buttonPressedSound();
        super.getSceneManager().switchToStartMenu();
    }
}
