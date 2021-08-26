package model.status.malus;

import Utilities.Parameters;
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
        
        this.setImage(Parameters.malusCommandImage);
        this.getNode().setRotate(0);
        this.setStatusName(StatusEnum.MalusCommand);
        this.setCoolDown(10); // 10 s

        this.setEffect(() -> this.getPlayer().setInvertedCommand(true));
        this.setRemoveEffect(() -> this.getPlayer().setInvertedCommand(false));
    }
}