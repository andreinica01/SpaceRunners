package model.hud;

import model.status.StatusEnum;

public interface IHUDBonus {
    
    /**
     * Loads the corrected image
     */
    public void loadStatus(StatusEnum statusToLoad);
}
