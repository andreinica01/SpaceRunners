package GameField;

import javafx.scene.canvas.Canvas;
import java.util.Set;

import GameObjects.BonusObject;
import GameObjects.Bullet;
import GameObjects.Entity;
import GameObjects.SpaceShip;




public interface GameField {

    /**
     * 
     * @return the draw panel where the content is draw
     */

    Canvas getCanvas();

    /**
     * Add an Entity to the gameField
     * 
     * @param entity
     */

    void addEntity(Entity entity);

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



}
