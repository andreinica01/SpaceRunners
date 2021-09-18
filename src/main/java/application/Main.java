package application;

import controller.gameSwitcher.SwitcherImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public final void start(final Stage mainWindow) {

    	new SwitcherImpl(mainWindow);
        mainWindow.setTitle("SpaceRunners");
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
