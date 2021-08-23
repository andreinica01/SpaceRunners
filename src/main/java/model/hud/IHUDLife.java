package model.hud;

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
