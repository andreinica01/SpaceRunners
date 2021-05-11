package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import GameLoop.*;


public class Main extends Application {
	
	@Override
	public void start(Stage mainwindow) {
		try {
			GameManager x = new GameManager(mainwindow);
			System.out.println(x);
			x.start();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
