package model.status.bonus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

public class BonusSpeed extends Status {
	
	public BonusSpeed () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
		setStatusName(StatusEnum.BonusSpeed);
		
		setEffect(() -> getPlayer().setSpeed((Integer)getPlayer().getSpeed() * 3/2));
		setRemoveEffect(() -> getPlayer().setSpeed((Integer)getPlayer().getSpeed() * 2/3));
		setDuration(10); 
	}
}
