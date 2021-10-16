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

public class ScoresController extends BasicFXMLController {
    
    @FXML
    private TextFlow scoreText;


    public ScoresController(Stage mainWindow, SceneManager sceneManager) throws IOException {
        super(mainWindow, sceneManager);
    }
    
    @FXML
    private void initialize() {
        this.setUpScoresScene();
    }
    
    private void setUpScoresScene() {
        this.refreshScoresInfo();
        this.scoreText.setVisible(true);
    }
    
    public void refreshScoresInfo() {
        Text rankText = new Text(this.getSceneManager().getRanking().getFormattedRankingMap());
        rankText.setFill(Color.GREEN);
        rankText.setFont(Font.font("Verdana", FontWeight.BOLD, MagicEnumDouble.SIXTEEN.getValue()));
        this.scoreText.getChildren().add(rankText);
    }
    
    @FXML
    void showStartMenu(ActionEvent event) throws IOException {
        this.buttonPressedSound();
        this.getSceneManager().switchToStartMenu();
    }

}
