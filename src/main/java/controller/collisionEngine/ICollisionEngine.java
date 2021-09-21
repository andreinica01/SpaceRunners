package controller.collisionEngine;

public interface ICollisionEngine {

	/** 
     * Handles collisions within the player and the game field. 
     */
	void collisionWalls();
	
	/** 
     * Handles collision between player and enemy ships.
     */
	void playerCollisionWithEnemies();
	
	/** 
     * Collision between player and bonus entities.
     */
	void playerBonusCollision();
	
	/** 
     * Collision between bullet and enemy entities. 
     */
	void bulletCollisionWithEnemies();
	
	/**
     * Handles Boss collision within game field.
     */
	void bossesCollisionwithWall();
	
	/**
     * Handles collisions between bullet and bosses.
     */
	void bossCollisionWithBullets();
}
