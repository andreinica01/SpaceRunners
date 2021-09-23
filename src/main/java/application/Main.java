package application;

import java.io.IOException;
import controller.gameSwitcher.ISwitcher;
import controller.gameSwitcher.SwitcherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public final void start(final Stage mainWindow) throws IOException {
    	mainWindow.setTitle("SpaceRunners");
    	mainWindow.setOnCloseRequest(e -> {
    		Platform.exit();
    		System.exit(0); 
    		});

    	ISwitcher switcher = new SwitcherImpl(mainWindow);
    	//switcher.startMenu();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
