package model.status.bonus;

import model.status.Status;
import model.status.StatusEnum;
import utilities.HUDParameters;
import utilities.VariousMagicNumbers;

/** 
 * A Status that, below a certain maximum, add 1 bonus life to the player. 
 */
public class BonusLife extends Status {

    /**
     * Constructor.
     */
    public BonusLife() {
        super();

        this.setStatusName(StatusEnum.BonusLife);
        this.setCoolDown(VariousMagicNumbers.ONE);

        this.setEffect(() -> {
            if (this.getPlayer().getLifePoints() < HUDParameters.MAX_LIFE_POINTS) {
                this.getPlayer().increaseLifePoints(VariousMagicNumbers.ONE);
            }
        });

        this.setRemoveEffect(() -> {
        });
    }
}
