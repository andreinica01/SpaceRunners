package controller.collisionEngine;

import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import model.hud.IHUDBonus;

/**
 * Helper interface that shows how to update a HUD part due to an event.
 */
public interface IHelper {

    /**
     * Checks for each game cycle if collisions are detected.
     */
    void update();

    /**
     * Subtract a life when an enemy ship hits the player.
     */
    void removeLife();

    /**
     * Subtract points when an enemy ship hits the player.
     */
    void removePoints();

    /**
     * Add points when an enemy ship is destroyed.
     */
    void addPoints();

    /**
     * Add a life when relative bonus is collected.
     */
    void addLife();

    /**
     * @return pointsHUD.
     */
    HUDPointsImpl getPointsHUD();

    /**
     * @return lifeHUD.
     */
    HUDLifeImpl getLifeHUD();

    /**
     * @return bonusHUD.
     */
    IHUDBonus getBonusHUD();

    /**
     * @return bossHP.
     */
    int getBossHP();

    /**
     * Helper method. Set a new value for boss HPs.
     * 
     * @param value
     */
    void setBossHP(int value);
}
