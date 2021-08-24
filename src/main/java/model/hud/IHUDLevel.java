package model.hud;

/**
 * This interface gives an indication how to handle a basic HUD level system.
 */
public interface IHUDLevel {

    /**
     * @return actual level.
     */
    int getActualLevel();

    /**
     * It increases the level.
     */
    void levelUp();
}
