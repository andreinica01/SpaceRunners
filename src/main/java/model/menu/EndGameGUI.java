package model.menu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.Parameters;
import utilities.VariousMagicNumbers;

public class EndGameGUI implements IEndGameGUI {

    private static final int X_LAYOUT = 500;
    private static final int Y_LAYOUT = 720;

    private Stage endGameWindow;
    private int points;

    /*
     * Constructor.
     */
    public EndGameGUI() throws IOException {

    	this.endGameWindow = new Stage();
    	this.endGameWindow.setTitle("Game is over, you lost! :c");
        this.endGameWindow.setResizable(false);
    	
        Pane root = new Pane();
    	root.setPrefSize(X_LAYOUT, Y_LAYOUT);

    	//Loading background
    	InputStream image = Files.newInputStream(Paths.get(Parameters.ImageFolder + "stars2.png"));
    	Image backGround = new Image(image);
    	image.close();
    	ImageView view = new ImageView(backGround);
    	view.setFitHeight(X_LAYOUT);
    	view.setFitWidth(Y_LAYOUT);
    	root.getChildren().add(view);

    	//Setting the scene
    	Scene scene = new Scene(root);
    	this.endGameWindow.setScene(scene);
    }

    @Override
    public final void end(final int points) {
        this.points = points;
    	this.endGameWindow.show();
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
