package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;
/**
 * A Status that block player's firing.
 */
public class MalusFire extends Status {

	public MalusFire() {
		super();
		//setImage(Parameters.bonusSpeedImage);
		//getNode().setRotate(0);
		setStatusName(StatusEnum.MalusFire);

		setEffect(() -> getPlayer().setCanFire(false));
		setRemoveEffect(() -> getPlayer().setCanFire(true));
		setDuration(8); // 8 s
	}
}
