package controller.gameEventController;

import controller.collisionEngine.PhysicsEngine;
import model.hud.HUDBonusImpl;
/**
 * This interface shows the methods that are used in order to handle the HUD
 * situations and coordinating the events with the game conditions. Detecting
 * collisions causes changes in HUD and in the stats of the player.
 */
public interface IGameEventController {
    /**
     * end the game loop and sets a new GUI showing the points earned and giving you
     * the choice beetween saving and quitting or just quitting.
     */
    void endGame();

    /**
     * Sets all HUD parts for the game.
     */
    void createHUD();

    /** 
     * @return the game status value: if false, the game ends. 
     */
    boolean checkGameStatus();

    /** 
     * @return the value of the points. 
     */
    int checkPoints();

    /** 
     * @return the remaining life points. 
     */
    int checkLives();

    /**
     * @return bonus HUD reference
     */
    HUDBonusImpl getBonusImpl();

    /** 
     * @return collision engine. 
     */
    PhysicsEngine getCollisionEngine();
}
