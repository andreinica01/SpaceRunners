package model.menu;

import java.io.IOException;
import controller.gameSwitcher.SceneManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utilities.VariousMagicNumbers;

public class EndGameGUI implements IEndGameGUI {

    private Stage endGameWindow;
    private SceneManager sceneManager;
    private int points;

    /*
     * Constructor.
     */
    public EndGameGUI(final SceneManager sceneManager, final int points) throws IOException {
    	this.sceneManager = sceneManager;
    	this.end(points);
    	
    }

    @Override
    public final void end(final int points) throws IOException {
        this.points = points;
        this.sceneManager.switchToEndMenu();
    }

    @Override
    public final void saveAndQuit() {
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
        saveButton.setOnAction(e -> {
            // save
            this.endGameWindow.close();
        });

        savePane.getChildren().addAll(nameLabel, nameInput, saveButton);

        Scene scene = new Scene(savePane, 330, 250);
        this.endGameWindow.setScene(scene);
        this.endGameWindow.setResizable(false);
        this.endGameWindow.show();
    }

    @Override
    public final void quit() {
        System.exit(VariousMagicNumbers.ZERO);
    }
}
