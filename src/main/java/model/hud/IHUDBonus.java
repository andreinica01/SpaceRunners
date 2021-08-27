package model.hud;

import javafx.scene.image.ImageView;

public interface IHUDBonus {

    /**
     * @return all bonuses taken.
     */
    ImageView[] getBonusTaken();

    /**
     * Add all bonuses.
     */
    void addBonuses();

    /**
     * Show a specific bonus.
     * @param index.
     */
    void showBonus(int index);

    /**
     * Hide a specific bonus.
     * @param index.
     */
    void hideBonus(int index);
}
