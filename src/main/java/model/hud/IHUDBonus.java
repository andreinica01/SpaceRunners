package model.hud;

import javafx.scene.image.ImageView;
import model.status.StatusEnum;

public interface IHUDBonus {

    /**
     * @return all bonuses taken.
     */
    ImageView[] getBonus();

    /**
     * Add all bonuses.
     */
    void addBonuses();

    /**
     * Show a specific bonus.
     * @param index.
     */
    void showBonus(StatusEnum status);

    /**
     * Hide a specific bonus.
     * @param index.
     */
    void hideBonus(StatusEnum status);
}
