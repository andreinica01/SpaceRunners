package model.hud;

/**
 * This interface gives an indication how to handle a basic HUD lives system.
 */
public interface IHUDLife {

    /**
     * @return life points.
     */
    public int getLifePoints();
    
    /**
     * It increases life points.
     */
    public void lifeUp();
    
    /**
     * It decreases life points.
     */
    public void lifeDown();
    
    /**
     * @return game status.
     */
    public boolean getStatus();
}
