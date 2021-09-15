package model.hud;

/**
 * This interface gives an indication how to handle a basic HUD points system.
 */
public interface IHUDPoints {

    /** 
     * @return actual points. 
     */
    int getPoints();

    /** 
     * It increases the points. 
     */
    void pointsUp();

    /** 
     * It decreases the points. 
     */
    void pointsDown();

    /**
     * Set the points to the value you decide. Helper method.
     * @param value to set.
     */
    void pointsSetter(int value);
}
