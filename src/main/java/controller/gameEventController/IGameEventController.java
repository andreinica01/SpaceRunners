package controller.gameEventController;

import javafx.scene.layout.AnchorPane;
import model.hud.HUDLifeImpl;

/**
 * This interface shows the methods that are used in order to handle the hud situations
 * and coordinating the event with the game conditions.
 * Detecting collisions causes changes in HUD and in game stats of the player.
 */
public interface IGameEventController {
    /**
     * end the game loop and sets a new GUI showing the points earned and giving you
     * the choice beetween saving and quitting or exit from the game without saving.
     */
    public void endGame();
    
    /**
     * Sets all HUD parts for the game.
     * @param the game panel and the lives
     */
    public void createHUD(AnchorPane gamePane, HUDLifeImpl lives);
    
    /**
     * @return the game status value: if false, end the game.
     */
    public boolean checkGameStatus();
    
    /**
     * @return the value of the points, it is needed to set the end game interface.
     */
    public int checkPoints();
    
    /**
     * @return the remaining life points.
     */
    public int checkLives();
    
    /**
     * @return actual level.
     */
    public int checkLevel();
}
