package model.hud;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.status.StatusEnum;

public class HUDBonusImpl implements IHUDBonus {

    /*
     * HUD parameters
     */
    private final static int SPACING = 50;

    /*
     * Control fields
     */
    private AnchorPane pane;
    private int activeBonus;
    private ImageView[] bonus = new ImageView[HUDParameters.TOTAL_BONUS];
    
    /*
     * Constructor
     */
    public HUDBonusImpl(final AnchorPane gamePane) {
        this.pane = gamePane;
        this.activeBonus = HUDParameters.ZERO;
        
        for(int i = HUDParameters.ZERO; i < this.bonus.length; i++) {
            
        }
    }

    @Override
    public StatusEnum getBonusTaken() {
        return null;
    }

    @Override
    public void addBonus() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeBonus() {
        // TODO Auto-generated method stub
        
    }
    
}
