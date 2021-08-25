package controller.collisionEngine;

/**
 * Collision detection methods.
 */
public interface PhysicsEngine {
  /**
   * Checks for each game cycle if collisions are detected.
   */
  void update();

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
}
