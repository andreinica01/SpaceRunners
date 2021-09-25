package application;

import java.io.IOException;

import controller.gameSwitcher.MenuController;
import controller.gameSwitcher.SceneManager;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public final void start(Stage mainWindow) throws IOException {
		mainWindow.setTitle("SpaceRunners");
		
		mainWindow.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		this.createStartMenu(mainWindow);
		mainWindow.show();
	}

	public static void main(final String[] args) {
		launch(args);
	}
	
	private void createStartMenu (Stage mainWindow) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/StartMenu.fxml"));
		loader.setController(new MenuController(new SceneManager(mainWindow)));
		mainWindow.setScene(new Scene(loader.load()));
	}
}
