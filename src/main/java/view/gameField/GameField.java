package view.gameField;


import java.util.Set;

import controller.inputController.InputControllerImpl;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import model.Entity;
import model.bonus.Bonus;
import model.bullet.Bullet;
import model.ship.SpaceShip;



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
    Set<Bonus> getBonusObjects();

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

   
    
    void setBackgroundImage(String path);
 
    
    Node []getBackground();

    void addBullet(Bullet bullet);


    void addEnemyShip(SpaceShip enemy);
    
    void addBonus (Bonus bonus);
  
}
