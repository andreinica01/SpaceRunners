package model.hud;

import javafx.scene.image.ImageView;
import model.status.StatusEnum;

public interface IHUDBonus {
    
    /**
     * @return bonus taken.
     */
    ImageView[] getBonusTaken();
    
    /**
     * Add all bonuses.
     */
    void addBonuses();
    
    void showBonus(StatusEnum bonus);
    
    void hideBonus(StatusEnum bonus);
}
