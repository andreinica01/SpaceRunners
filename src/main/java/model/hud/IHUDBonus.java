package model.hud;

import javafx.scene.image.ImageView;
import model.status.StatusEnum;

public interface IHUDBonus {

    /**
     * @return all bonuses taken.
     */
    ImageView[] getBonus();

    /**
     * Show a specific bonus.
     * 
     * @param index.
     */
    void showBonus(StatusEnum status);

    /**
     * Hide a specific bonus.
     * 
     * @param index.
     */
    void hideBonus(StatusEnum status);

    /**
     * @param index.
     * @return which status is activated.
     */
    boolean getTracker(int index);
}
