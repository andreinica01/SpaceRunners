package view.hud;

import controller.collisionEngine.IHelper;
import model.hud.IHUDBonus;

public interface IHUD {

    /**
     * @return the game status value: if false, the game ends.
     */
    boolean checkGameStatus();

    /**
     * @return the value of the points.
     */
    int checkPoints();

    /**
     * @return the remaining life points.
     */
    int checkLives();

    /**
     * @return bonus HUD reference.
     */
    IHUDBonus getBonusImpl();

    /**
     * @return collision engine.
     */
    IHelper getCollisionEngine();
}
