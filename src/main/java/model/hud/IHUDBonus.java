package model.hud;

import model.status.StatusEnum;

public interface IHUDBonus {
    
    /**
     * @return life points.
     */
    public StatusEnum getBonusTaken();
    
    /**
     * It increases life points.
     */
    public void addBonus();
    
    /**
     * It decreases life points.
     */
    public void removeBonus();
}
