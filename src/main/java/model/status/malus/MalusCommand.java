package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;
/**
 * A Status that reverses player's movement commands.
 */
public class MalusCommand extends Status {

	public MalusCommand() {
		super();
		//setImage(Parameters.bonusSpeedImage);
		//getNode().setRotate(0);
		setStatusName(StatusEnum.MalusCommand);

		setEffect(() -> getPlayer().setInvertedCommand(true));
		setRemoveEffect(() -> getPlayer().setInvertedCommand(false));
		setDuration(8); // 8 s
	}
}
