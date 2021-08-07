package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

public class MalusCommand extends Status {

	public MalusCommand() {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
		setStatusName(StatusEnum.MalusCommand);

		setEffect(() -> getPlayer().setInvertedCommand(true));
		setRemoveEffect(() -> getPlayer().setInvertedCommand(false));
		setDuration(4); // 8 s
	}
}
