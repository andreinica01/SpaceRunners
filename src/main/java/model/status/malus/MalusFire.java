package model.status.malus;

import model.status.Status;
import model.status.StatusEnum;
import utilities.VariousMagicNumbers;

/** 
 * A Status that block player's firing. 
 */
public class MalusFire extends Status {

    /**
     * Constructor.
     */
    public MalusFire() {
        super();

        this.setStatusName(StatusEnum.MalusFire);
        this.setCoolDown(VariousMagicNumbers.SEVEN); // 7 s

        this.setEffect(() -> this.getPlayer().setCanFire(VariousMagicNumbers.FALSE));
        this.setRemoveEffect(() -> this.getPlayer().setCanFire(VariousMagicNumbers.TRUE));
    }
}
