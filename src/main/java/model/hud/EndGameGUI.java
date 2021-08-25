package model.hud;

import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EndGameGUI implements IEndGameGUI {

  private static final int X_LAYOUT = 600;
  private static final int Y_LAYOUT = 400;

  private Stage endGameWindow;
  private int points;
  private int level;

  @Override
  public void end(int points, int level) throws Exception {
    this.points = points;
    this.level = level;

    this.endGameWindow = new Stage();
    this.endGameWindow.setTitle("Game is over, you lost! :c");
    this.endGameWindow.setResizable(false);

    Parent root = FXMLLoader.load(getClass().getResource("EndGameGui.fxml"));
    endGameWindow.setScene(new Scene(root, X_LAYOUT, Y_LAYOUT));
    endGameWindow.show();
  }

  @Override
  public void saveAndQuit() {
    GridPane savePane = new GridPane();
    savePane.setPadding(new Insets(0, 10, 10, 10));
    savePane.setVgap(10);
    savePane.setHgap(10);

    Label nameLabel = new Label("Player's name: ");
    GridPane.setConstraints(nameLabel, 0, 0);

    TextField nameInput = new TextField();
    GridPane.setConstraints(nameInput, 1, 0);

    Button saveButton = new Button("Save and quit.");
    saveButton.setMinWidth(200);
    GridPane.setConstraints(saveButton, 1, 1);
    saveButton.setOnAction(
      e -> {
        //save
        this.endGameWindow.close();
      }
    );

    savePane.getChildren().addAll(nameLabel, nameInput, saveButton);

    Scene scene = new Scene(savePane, 330, 250);
    this.endGameWindow.setScene(scene);
    this.endGameWindow.setResizable(false);
    this.endGameWindow.show();
  }

  @Override
  public void quit() {
    // TODO Auto-generated method stub

  }
}
