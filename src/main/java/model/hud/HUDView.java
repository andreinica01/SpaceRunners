package model.hud;

/**
 * This interface shows the specific methods of our HUD that must be implemented.
 */
public interface HUDView {

    /**
     * It increases life points.
     */
    public void lifeUp();
    
    /**
     * It decreases life points.
     */
    public void lifeDown();
    
    /**
     * @return life points.
     */
    public int getLifePoints();
}
