package model.hud;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EndGameGUI implements IEndGameGUI {

    private final static int X_LAYOUT = 800;
    private final static int Y_LAYOUT = 650;
    
    private Button exitButton;
    private Button saveAndExitButton;
    private VBox layout;
    private Scene scene;
    private Stage endGameWindow;
    private int points;
    private int level;
    
    @Override
    public void end(int points, int level) {
        this.points = points;
        this.level = level;
        
        this.endGameWindow = new Stage();
        this.endGameWindow.setTitle("Game is over, you lost! :c");
        this.endGameWindow.setResizable(false);
        
        this.exitButton = new Button("Quit");
        this.saveAndExitButton = new Button("Save and Quit");
        
        this.exitButton.setOnAction(e -> this.quit());
        this.saveAndExitButton.setOnAction(e -> this.saveAndQuit()); 
        
        this.exitButton.setPrefWidth(167);
        this.saveAndExitButton.setPrefWidth(167);
        this.exitButton.setPrefHeight(59);
        this.saveAndExitButton.setPrefHeight(59);
        
        this.exitButton.setLayoutX(314);
        this.exitButton.setLayoutY(245);
        this.exitButton.setLayoutX(120);
        this.exitButton.setLayoutY(245);
        
        endGameWindow.setScene(scene);
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
        saveButton.setOnAction(e -> {
            //save
            this.endGameWindow.close();
        });
        
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
