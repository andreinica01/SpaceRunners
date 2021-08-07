package model.status.bonus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

public class BonusLife extends Status{
	
	
	public BonusLife () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
		setStatusName(StatusEnum.BonusLife);
		
		setEffect(() -> {
			if (getPlayer().getLifePoints() < 4 && getPlayer().getLifePoints() > 0)
				getPlayer().setLifePoints(getPlayer().getLifePoints() + 1);});
		setRemoveEffect(() -> {});
		setDuration(0); 
	}
}
