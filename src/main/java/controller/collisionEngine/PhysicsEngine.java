package controller.collisionEngine;

/** 
 * Collision detection methods. 
 */
public interface PhysicsEngine {

    /**
     * @return remaining boss HP
     */
    int getBossHP();

    /**
     * Checks for each game cycle if collisions are detected.
     */
    void update();

    /**
     * Subtract a life when an enemy ship hits the player.
     */
    void removeLife();

    /**
     * Subtract points when an enemy ship hits the player.
     */
    void removePoints();

    /**
     * Add points when an enemy ship is destroyed.
     */
    void addPoints();

    /**
     * Add a life when relative bonus is collected.
     */
    void addLife();
}
