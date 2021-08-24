package controller.collisionEngine;

/**
 * I decide to consider each entity as a circle. It is the most simple way to define
 * a working collision detection logic.
 */
public interface PhysicsEngine {
    
    /**
     * This is an helper method used to check collision between objects
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */

    /**
     * Subtract a life when an enemy ship hits the player
     * @return 
     */
    void removeLife();
    
    /**
     * Subtract points when an enemy ship hits the player
     */
    void removePoints();
    
    /**
     * Add points when an enemy ship is destroyed
     */
    void addPoints();
    
    /**
     * Detects collision with border
     */
    void playerCollsionBorders();
    
    /**
     * Remove entities which collide
     */
    void removeCollidedShips();
    
    /**
     * Detects player collision
     */
    void playerShipCollision(); 
}
