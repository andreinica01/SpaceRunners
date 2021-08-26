package model.status.bonus;

import Utilities.HUDParameters;
import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

/** 
 * A Status that, below a certain maximum, add 1 bonus life to the player. 
 */
public class BonusLife extends Status {

    /**
     * Constructor
     */
    public BonusLife() {
        super();
        
        this.setImage(Parameters.bonusLifeImage);
        this.setStatusName(StatusEnum.BonusLife);
        this.setCoolDown(0);

        this.setEffect(() -> {
            if (this.getPlayer().getLifePoints() < HUDParameters.MAX_LIFE_POINTS)
                this.getPlayer().increaseLifePoints(HUDParameters.ONE);
        });

        this.setRemoveEffect(() -> {
        });
    }
}