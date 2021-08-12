package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;
/**
 * A Status that decrease player's speed of a certain amount.
 */
public class MalusSpeed extends Status {
	
	private int decreaseFactor = 1/3;

	public MalusSpeed() {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
		setStatusName(StatusEnum.MalusSpeed);

		setEffect(() -> getPlayer().setSpeed(getPlayer().getSpeed().floatValue() * decreaseFactor));
		setRemoveEffect(() -> getPlayer().setSpeed(getPlayer().getSpeed().floatValue() * 1/decreaseFactor));
		setDuration(4); // 8 s
	}
}
