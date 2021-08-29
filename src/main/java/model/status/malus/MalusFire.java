package model.status.malus;

import Utilities.HUDParameters;
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
        
        this.getNode().setRotate(HUDParameters.ZERO);
        this.setStatusName(StatusEnum.MalusFire);
        this.setCoolDown(HUDParameters.SEVEN); // 7 s

        this.setEffect(() -> this.getPlayer().setCanFire(HUDParameters.FALSE));
        this.setRemoveEffect(() -> this.getPlayer().setCanFire(HUDParameters.TRUE));
    }
}