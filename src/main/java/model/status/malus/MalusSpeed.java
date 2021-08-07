package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

public class MalusSpeed extends Status {

	public MalusSpeed() {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
		setStatusName(StatusEnum.MalusSpeed);

		setEffect(() -> getPlayer().setSpeed(getPlayer().getSpeed().floatValue() * 1 / 3));
		setRemoveEffect(() -> getPlayer().setSpeed(getPlayer().getSpeed().floatValue() * 3));
		setDuration(4); // 8 s
	}
}
