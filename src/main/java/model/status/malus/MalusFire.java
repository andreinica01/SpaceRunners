package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

/** 
 * A Status that block player's firing. 
 */
public class MalusFire extends Status {

    /**
     * Constructor
     */
    public MalusFire() {
        super();
        
        this.setImage(Parameters.malusFireImage);
        this.getNode().setRotate(0);
        this.setStatusName(StatusEnum.MalusFire);
        this.setCoolDown(7); // 7 s

        this.setEffect(() -> this.getPlayer().setCanFire(false));
        this.setRemoveEffect(() -> this.getPlayer().setCanFire(true));
    }
}