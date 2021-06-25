package controller.collisionEngine;

import java.util.Set;
import model.Entity;

public interface PhysicsEngine {

    /**
     * return a set with all the enemyShips hitted by a bullet
     */
    Set<Entity> damagedEnemies();

    /**
     * 
     * @return check if the player was hittede by a bullet
     */
    boolean isPlayerHitted();

    /**
     * 
     * @return if the player is at the margin of the canvas
     */
    boolean isPlayeratMargin();

}
