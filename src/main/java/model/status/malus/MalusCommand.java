package model.status.malus;

import Utilities.HUDParameters;
import model.status.Status;
import model.status.StatusEnum;

/**
 * A Status that reverses player's movement commands.
 */
public class MalusCommand extends Status {

    /**
     * Constructor
     */
    public MalusCommand() {
        super();
        
        this.setStatusName(StatusEnum.MalusCommand);
        this.setCoolDown(HUDParameters.TEN); // 10 s

        this.setEffect(() -> this.getPlayer().setInvertedCommand(HUDParameters.TRUE));
        this.setRemoveEffect(() -> this.getPlayer().setInvertedCommand(HUDParameters.FALSE));
    }
}