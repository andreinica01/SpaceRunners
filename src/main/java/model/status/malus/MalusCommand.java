package model.status.malus;

import model.status.Status;
import model.status.StatusEnum;
import utilities.MagicEnumInt;

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
		this.setCoolDown(MagicEnumInt.TEN.getValue()); // 10 s

		this.setEffect(() -> this.getPlayer().setInvertedCommand(true));
		this.setRemoveEffect(() -> this.getPlayer().setInvertedCommand(false));
	}
}
