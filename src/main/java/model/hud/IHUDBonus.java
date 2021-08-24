package model.hud;

import model.status.StatusEnum;

public interface IHUDBonus {
    
    /**
     * @return life points.
     */
    StatusEnum getBonusTaken();
    
    /**
     * It increases life points.
     */
    void addBonus(int index);
    
    /**
     * It decreases life points.
     */
    void removeBonus();
}
