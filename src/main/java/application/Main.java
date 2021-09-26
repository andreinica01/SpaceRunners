package application;

import java.io.IOException;
import controller.gameSwitcher.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public final void start(final Stage mainWindow) throws IOException, ClassNotFoundException {
		mainWindow.setTitle("SpaceRunners");
		mainWindow.setResizable(false);
		mainWindow.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		SceneManager sm = new SceneManager(mainWindow);
		sm.switchToStartMenu();
	}

	public static void main(final String[] args) {
		launch(args);
	}
}
