package model.hud;

import javafx.scene.control.Label;

/**
 * This class defines how the level HUD must look.
 */
public class HUDLevelImpl extends Label implements IHUDLevel {

    /*
     * Control field
     */
    private int level;

    @Override
    public int getActualLevel() {
        return this.level;
    }

    @Override
    public void levelUp() {
        // TODO Auto-generated method stub
        
    }
}
