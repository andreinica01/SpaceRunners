package view.hud;

import controller.collisionEngine.PhysicsEngine;
import model.hud.HUDBonusImpl;

public interface IHUD {

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
     * @return bonus HUD reference.
     */
    HUDBonusImpl getBonusImpl();

    /** 
     * @return collision engine. 
     */
    PhysicsEngine getCollisionEngine();
}
