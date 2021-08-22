package model.hud;

/**
 * This interface shows the specific methods of our HUD that must be implemented. It is needed to 
 * handle points, current level and lifepoints. Font for words must be set here!
 */
public interface IHUDPoints {
    
    /**
     * @return actual points.
     */
    public int getPoints();
    
    /**
     * @return actual level.
     */
    public int getActualLevel();

    /**
     * It increases the points.
     */
    public void pointsUp();
    
    /**
     * It decreases the points.
     */
    public void pointsDown();
    
    /**
     * Set the points to the value you decide.
     * @param value to set
     */
    public void pointsSetter(int value);
    
    /**
     * It increases the level
     */
    public void levelUp();
}
