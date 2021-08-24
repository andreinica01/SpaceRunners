package model.status.malus;

import model.status.Status;
import model.status.StatusEnum;
/**
 * A Status that reverses player's movement commands.
 */
public class MalusCommand extends Status {

	public MalusCommand() {
		super();
		getNode().setRotate(0);
		setStatusName(StatusEnum.MalusCommand);
		setCoolDown(10); //10 s

		setEffect(() -> getPlayer().setInvertedCommand(true));
		setRemoveEffect(() -> getPlayer().setInvertedCommand(false));
	}
}
