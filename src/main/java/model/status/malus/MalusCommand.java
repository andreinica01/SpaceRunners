package model.status.malus;

import model.status.Status;
import model.status.StatusEnum;
import utilities.VariousMagicNumbers;

/**
 * A Status that reverses player's movement commands.
 */
public class MalusCommand extends Status {

    /**
     * Constructor.
     */
    public MalusCommand() {
        super();

        this.setStatusName(StatusEnum.MalusCommand);
        this.setCoolDown(VariousMagicNumbers.TEN); // 10 s

        this.setEffect(() -> this.getPlayer().setInvertedCommand(VariousMagicNumbers.TRUE));
        this.setRemoveEffect(() -> this.getPlayer().setInvertedCommand(VariousMagicNumbers.FALSE));
    }
}
