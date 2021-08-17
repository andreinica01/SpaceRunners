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
    public double checkDistance(final double x1, final double x2, final double y1, final double y2);
    
    /**
     * Subtract a life when an enemy ship hits the player
     */
    public void removeLife();
    
    /**
     * Subtract points when an enemy ship hits the player
     */
    public void removePoints();
    
    /**
     * Checks entities collision
     */
    public void checkIfElementsCollide();
}
