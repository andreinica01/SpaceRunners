package model.status.bonus;

import model.status.Status;
import model.status.StatusEnum;
import utilities.MagicEnumInt;

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
        this.setCoolDown(MagicEnumInt.ONE.getValue());

        this.setEffect(() -> {
            if (this.getPlayer().getLifePoints() < MagicEnumInt.FOUR.getValue()) {
                this.getPlayer().increaseLifePoints(MagicEnumInt.ONE.getValue());
            }
        });

        this.setRemoveEffect(() -> {
        });
    }
}
