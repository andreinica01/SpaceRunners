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
    public void removeLife();
    
    /**
     * Subtract points when an enemy ship hits the player
     */
    public void removePoints();
    
    /**
     * Add points when an enemy ship is destroyed
     */
    public void addPoints();
    
    /**
     * Detects collision with border
     */
    public void playerCollsionBorders();
    
    /**
     * Remove entities which collide
     */
    public void removeCollidedShips();
    
    /**
     * Detects player collision
     */
    public void playerShipCollision();
    
}
