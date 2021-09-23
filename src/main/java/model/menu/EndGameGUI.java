package model.menu;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.Parameters;
import utilities.VariousMagicNumbers;

public class EndGameGUI implements IEndGameGUI {

    private static final int X_LAYOUT = 600;
    private static final int Y_LAYOUT = 400;

    private Stage endGameWindow;
    private int points;

    /*
     * Constructor.
     */
    public EndGameGUI() {
    	this.endGameWindow = new Stage();

    	Pane root = new Pane();
    	root.setPrefSize(X_LAYOUT, Y_LAYOUT);

    	//root.setBackground(new Image(new File(Parameters.backgroundImage)));
    }

    @Override
    public final void end(final int points) {
        this.points = points;

        this.endGameWindow.setTitle("Game is over, you lost! :c");
        this.endGameWindow.setResizable(false);
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
        this.endGameWindow.close();
        System.exit(VariousMagicNumbers.ZERO);
    }
}
