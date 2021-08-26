package controller.collisionEngine;

/** Collision detection methods. */
public interface PhysicsEngine {
  
  /** 
   * Checks for each game cycle if collisions are detected. 
   */
  void update();

  /**
   * Subtract a life when an enemy ship hits the player
   */
  void removeLife();

<<<<<<< HEAD

  /** Subtract points when an enemy ship hits the player */
=======
  /** 
   * Subtract points when an enemy ship hits the player 
   */
>>>>>>> 0bc8649ec75622e4fd2cad2f1ceb94d82f803076
  void removePoints();

  /**
   * Add points when an enemy ship is destroyed 
   */
  void addPoints();
  
  /**
   * Add a life when relative bonus is collected.
   */
  void addLife();
}
