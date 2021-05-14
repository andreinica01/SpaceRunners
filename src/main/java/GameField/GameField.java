package GameField;


import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


import java.util.Set;


import GameObjects.BonusObject;
import GameObjects.Bullet;
import GameObjects.Entity;
import GameObjects.SpaceShip;
import InputController.InputControllerImpl;
import javafx.scene.Node;



public interface GameField {

    /**
     * 
     * @return the draw panel where the content is draw
     */

   // Group getGroup();
        AnchorPane getGameContainer();



    /**
     * 
     * @return the active entities on the Gamefield
     */
    Set<Entity> getActiveEntities();

/**
 * 
 * @return Width 
 */

    Number getWidth();

    /*
     * @return Height 
     */
    Number getHeight();

    /**
     * 
     * @param add the player Entity to the game
     */
    void setPlayer(SpaceShip player);

    /**
     * 
     * @return the player Entity
     */
    SpaceShip getPlayer();

    /**
     * 
     * @return a set with all the current enemy ships on the field
     */
    Set<SpaceShip> getActiveEnemyShips();

    /**
     * 
     * @return a set with all the Bonus Objects in the game
     */
    Set<BonusObject> getBonusObjects();

    /**
     * 
     * @return all active bullets shot by the player
     */
    Set<Bullet> getActiveBulletsShotbyPlayer();

    /**
     * 
     * @return all active bullets shot towards the player
     */
    Set<Bullet> getActiveBulletsShotbyEnemies();


    void setInputController(InputControllerImpl controller);


    void setScene(Scene scene);

    Scene getScene();

    
    void setBackgroundImage(String path);

    Node getBackground();

    void addBullet(Bullet bullet);
  
}
