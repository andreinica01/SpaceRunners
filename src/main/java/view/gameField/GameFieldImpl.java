package view.gameField;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import Utilities.Parameters;
import controller.inputController.InputControllerImpl;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Entity;
import model.bullet.Bullet;
import model.ship.SpaceShip;
import model.status.Status;
import view.SoundManager.SoundManager;

public class GameFieldImpl implements GameField {

	private Set<Entity> entities;
	private Set<Bullet> bullets;
	private Set<SpaceShip> enemyships;
	private Set<Status> status;
	
	private SpaceShip player;

	private ImageView[] backGroundImage;

	private Scene scene;

	private final int width;
	private final int height;


	private SoundManager soundmanager;
	private AnchorPane gameContainer;

	public GameFieldImpl(final int width, final int height) {

		this.gameContainer = new AnchorPane();

		this.gameContainer.prefWidth(width);
		this.gameContainer.prefHeight(height);

		this.entities = new HashSet<Entity>();
		this.bullets = Collections.synchronizedSet(new HashSet<Bullet>());
		this.enemyships = Collections.synchronizedSet( new HashSet<SpaceShip>());
		this.status = new HashSet<Status>();

		//to load in specific class container
		this.backGroundImage = new ImageView[2];
		
		this.width = width;
		this.height = height;

		this.soundmanager = new SoundManager();

		
	}

	public AnchorPane getGameContainer() {
		return this.gameContainer;
	}

	@Override
	public Set<Entity> getActiveEntities() {
		return this.entities;
	}

	@Override
	public Number getWidth() {
		return this.width;
	}

	@Override
	public Number getHeight() {
		return this.height;
	}

	@Override
	public void setPlayer(SpaceShip player) {
		this.player = player;
		this.gameContainer.getChildren().add(this.player.getNode());
		this.entities.add(player);
	}

	@Override
	public SpaceShip getPlayer() {
		return this.player;
	}

	@Override
	public Set<SpaceShip> getActiveEnemyShips() {
		return this.enemyships;
	}

	@Override
	public Set<Status> getBonusObjects() {
		return this.status;
	}

	@Override
	public Set<Bullet> getActiveBulletsShotbyPlayer() {
		return this.bullets;
	}

	@Override
	public Set<Bullet> getActiveBulletsShotbyEnemies() {
		return null;
	}

	@Override
	public void setInputController(InputControllerImpl controller) {
		//this.scene.addEventHandler(KeyEvent.ANY, controller);

	}

	@Override
	public void setScene(Scene scene) {
		this.scene = scene;

	}

	@Override
	public void setBackgroundImage(String path) {

		for (int i = 0; i < this.backGroundImage.length; i++) {
			this.backGroundImage[i] = new ImageView(Parameters.BackgroundImage);
			this.backGroundImage[i].setLayoutX(0);
			this.backGroundImage[i].setFitWidth(this.width);
			this.backGroundImage[i].setFitHeight(this.height);
			this.backGroundImage[i].setViewOrder(+10);
			this.gameContainer.getChildren().add(this.backGroundImage[i]);
		}

		this.backGroundImage[0].setTranslateX(0);
		this.backGroundImage[1].setLayoutY(-this.height);

	}

	@Override
	public Node[] getBackground() {
		return this.backGroundImage;
	}

	@Override
	public void addBullet(Bullet bullet) {
		this.entities.add(bullet);
		this.bullets.add(bullet);
		this.gameContainer.getChildren().add(bullet.getNode());
	}

	@Override
	public void addEnemyShip(SpaceShip enemy) {
		this.enemyships.add(enemy);
		this.entities.add(enemy);
		this.gameContainer.getChildren().add(enemy.getNode());
	}

	@Override
	public void addBonus(Status bonus) {
		this.entities.add(bonus);
		this.status.add(bonus);
		this.gameContainer.getChildren().add(bonus.getNode());
	}

	@Override
        public Scene getScene() {
            return this.scene;
        }
	
	@Override
	public SoundManager getSoundManager() {
		return this.soundmanager;
	}
}
