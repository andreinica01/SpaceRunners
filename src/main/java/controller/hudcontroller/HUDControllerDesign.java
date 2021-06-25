package controller.hudcontroller;

/**
 * This interface shows the methods that are used in order to handle the hud situations
 * and coordinating the event with the game conditions.
 */
public interface HUDControllerDesign {

    /**
     * Sets all HUD parts for the game.
     */
    public void createHUD();
    
    /**
     * @return the game status value: if false, end the game.
     */
    public boolean checkGameStatus();
    
    /**
     * @return the value of the points, it is needed to set the end game interface.
     */
    public boolean checkPoints();
    
    /**
     * @return the remaining life points.
     */
    public int checkLives();
     
    /**
     * end the game loop and sets a new GUI showing the points earned and giving you
     * the choice beetween saving and quitting or exit from the game without saving.
     */
    public void endGame();

}
