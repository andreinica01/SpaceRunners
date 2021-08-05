package model.hud;

/**
 * This interface shows the basic design of our HUD.
 */
public interface HUDDesign {
    
    /**
     * It increases the points.
     */
    public abstract void pointsUp();
    
    /**
     * It decreases the points.
     */
    public abstract void pointsDown();
    
    /**
     * It increases the level
     */
    public abstract void levelUp();
    
    /**
     * Set the font of the chosen label.
     */
    public void setLabelFont();
    
    /**
     * @return actual points.
     */
    public int getPoints();
    
    /**
     * Set the point counter to a specified value.
     * @param value to add.
     */
    public void pointSetter(final int value);
    
    /**
     * @return game condition status.
     */
    public boolean getGameCondition();
    
    /**
     * Set the game condition status.
     * @param value
     */
    public void setGameCondition(final boolean value);
    
    /**
     * @return actual level.
     */
    public int getActualLevel();
    
    /**
     * It increases level.
     */
    public void increaseLevel();
}
