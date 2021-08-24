package model.hud;

import javafx.scene.image.ImageView;

public interface IHUDBonus {
    
    /**
     * @return bonus taken.
     */
    ImageView[] getBonusTaken();
    
    /**
     * Add all bonuses.
     */
    void addBonuses();
    
    void showBonus(int index);
    
    void hideBonus(int index);
}
