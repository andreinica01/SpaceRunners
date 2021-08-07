package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

public class MalusFire extends Status {

	public MalusFire() {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
		setStatusName(StatusEnum.MalusFire);

		setEffect(() -> getPlayer().setCanFire(true));
		setRemoveEffect(() -> getPlayer().setCanFire(false));
		setDuration(8); // 8 s
	}
}
