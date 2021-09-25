package model.menu;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import utilities.HUDParameters;
import utilities.VariousMagicNumbers;

public class MenuButtons extends StackPane {

	private Text text;
	private Rectangle hitBox;
	private DropShadow drop;
	
	public MenuButtons(final String name) {
		this.text = new Text(name);
		this.text.setFont(new Font(HUDParameters.FONT, HUDParameters.FONT_SIZE));

		this.hitBox = new Rectangle(250, 30);
		this.hitBox.setOpacity(0.6);
		this.hitBox.setFill(Color.BLACK);
		this.hitBox.setEffect(new GaussianBlur(3.5));
		
		this.setAlignment(Pos.CENTER_LEFT);
		this.getChildren().addAll(hitBox, this.text);
		
		//look
		this.setOnMouseEntered(e -> {
			this.hitBox.setTranslateX(10);
			this.text.setTranslateX(10);
			
			this.hitBox.setFill(Color.WHITE);
			this.text.setFill(Color.BLACK);
		});
		
		this.setOnMouseExited(e -> {
			this.hitBox.setTranslateX(0);
			this.text.setTranslateX(0);
			
			this.hitBox.setFill(Color.BLACK);
			this.text.setFill(Color.WHITE);
		});
		
		this.drop = new DropShadow(50, Color.WHITE);
		this.drop.setInput(new Glow());
		
		this.setOnMousePressed(e -> {
			this.setEffect(drop);
		});
		
		this.setOnMouseReleased(e -> {
			this.setEffect(null);
		});
	}
	
	private class GameMenu {

		private MenuButtons restart;
		private MenuButtons saveAndExit;
		private MenuButtons exit;
		
		private VBox menu0;
		private VBox menu1;
		
		public GameMenu() {
			this.menu0 = new VBox(10);
			this.menu1 = new VBox(10);

			this.menu0.setTranslateX(100);
			this.menu0.setTranslateY(200);
			this.menu1.setTranslateX(100);
			this.menu1.setTranslateY(200);

			this.restart = new MenuButtons("Restart");
			this.restart.setOnMouseClicked(e -> {
				//call to game manager

				FadeTransition transition = new FadeTransition(Duration.seconds(0.5));
				transition.setFromValue(1);
				transition.setToValue(0);
				transition.setOnFinished(ev -> setVisible(true));
				transition.play();
			});
			
			this.saveAndExit = new MenuButtons("Save and Exit");
			this.saveAndExit.setOnMouseClicked(e -> {
				//save
				System.exit(VariousMagicNumbers.ZERO);
			});
			
			this.exit = new MenuButtons("Exit");
			this.exit.setOnMouseClicked(e -> {
				System.exit(VariousMagicNumbers.ZERO);
			});
			
			this.menu0.getChildren().addAll(this.restart, this.saveAndExit, this.exit);
			
			Rectangle bg = new Rectangle(800, 600);
			bg.setFill(Color.GRAY);
			bg.setOpacity(0.4);
			
			getChildren().addAll(bg, menu0);
		}
	}
}
