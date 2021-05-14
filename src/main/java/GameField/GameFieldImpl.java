package GameField;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.Set;


import GameObjects.BonusObject;
import GameObjects.Bullet;
import GameObjects.Entity;
import GameObjects.SpaceShip;
import InputController.InputControllerImpl;

import Utilities.Parameters;
import java.io.File;
import javafx.scene.Node;
public class GameFieldImpl implements GameField {

    // private Canvas gameCanvas;
    private Set<Entity> entities;
    private Set<Bullet> bullets;


    private SpaceShip player;
    private ImageView backGroundImage;

    private Scene scene;

    private final int width;
    private final int height;

    // private Group gameContainer;
    private AnchorPane gameContainer;

    public GameFieldImpl(final int width, final int height) {

        // this.gameContainer = new Group();
        this.gameContainer = new AnchorPane();
        this.gameContainer.prefWidth(width);
        this.gameContainer.prefHeight(height);

        this.entities = new HashSet<Entity>();
        this.bullets = new HashSet<Bullet>();

        this.width = width;
        this.height = height;

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

        return null;
    }

    @Override
    public Set<SpaceShip> getActiveEnemyShips() {

        return null;
    }

    @Override
    public Set<BonusObject> getBonusObjects() {

        return null;
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

        this.scene.addEventHandler(KeyEvent.ANY, controller);

    }

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;

    }

    @Override
    public void setBackgroundImage(String path) {

        this.backGroundImage = new ImageView(new Image(new File(Parameters.ImageFolder + path).toURI().toString()));

        this.backGroundImage.setLayoutX(0);
        this.backGroundImage.setLayoutY(0);

        this.backGroundImage.setFitWidth(this.width);
        this.backGroundImage.setFitHeight(this.height);

        this.backGroundImage.setViewOrder(+10);

       this.gameContainer.getChildren().add(this.backGroundImage);
    }

    @Override
    public Node getBackground() {

        return this.backGroundImage;
    }

    @Override
    public Scene getScene() {
       return this.scene;
    }

    @Override
    public void addBullet(Bullet bullet) {

        this.entities.add(bullet);
        this.bullets.add(bullet);
        this.gameContainer.getChildren().add(bullet.getNode());

        
    }

}
