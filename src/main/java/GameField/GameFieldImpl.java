package GameField;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.Set;

import GameObjects.BonusObject;
import GameObjects.Bullet;
import GameObjects.Entity;
import GameObjects.SpaceShip;
import InputController.InputControllerImpl;

public class GameFieldImpl implements GameField {

    //private Canvas gameCanvas;
    private Set<Entity> entities;
    private SpaceShip player;

    private final int width;
    private final int height;

    //private Group gameContainer;
    private AnchorPane gameContainer;


    public GameFieldImpl(final int width, final int height) {
     
       // this.gameContainer = new Group();
        this.gameContainer = new AnchorPane();
        this.gameContainer.prefWidth(width);
        this.gameContainer.prefHeight(height);

        this.entities = new HashSet<Entity>();

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
        
        return null;
    }

    @Override
    public Set<Bullet> getActiveBulletsShotbyEnemies() {
       
        return null;
    }

    @Override
    public void setInputController(InputControllerImpl controller) {

        this.gameContainer.addEventHandler(KeyEvent.KEY_PRESSED, controller);
   

    }

}
