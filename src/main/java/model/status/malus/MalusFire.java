package model.status.malus;

import model.status.Status;
import model.status.StatusEnum;
import utilities.MagicEnumInt;

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
		this.setCoolDown(MagicEnumInt.SEVEN.getValue()); // 7 s

		this.setEffect(() -> this.getPlayer().setCanFire(false));
		this.setRemoveEffect(() -> this.getPlayer().setCanFire(true));
	}
}
