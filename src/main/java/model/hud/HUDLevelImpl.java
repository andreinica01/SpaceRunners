package model.hud;

import javafx.scene.control.Label;

/**
 * This class defines how the level HUD must look.
 */
public class HUDLevelImpl extends Label implements IHUDLevel {

    /*
     * HUDStructure
     */
    private static final int LEVEL_UP = 1;
    
    /*
     * Control field
     */
    private int level;
    
    public HUDLevelImpl() {
        

        this.level = LEVEL_UP;
    }

    @Override
    public int getActualLevel() {
        return this.level;
    }

    @Override
    public void levelUp() {
        this.level++; 
    }
}
