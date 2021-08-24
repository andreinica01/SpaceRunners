package controller.collisionEngine;

/**
 * I decide to consider each entity as a circle. It is the most simple way to define
 * a working collision detection logic.
 */
public interface PhysicsEngine {
    
    /**
     * Subtract a life when an enemy ship hits the player
     * @return 
     */

     
    void update();


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
     * Remove entities which collide
     */
    public void bulletCollsionwithEnemies();
    
    /**
     * Detects player collision
     */
    public void playerCollisionWithEnemies();


    /**
     * Detects collisionwithBonus
     */
    public void playerBonusCollision();
}
