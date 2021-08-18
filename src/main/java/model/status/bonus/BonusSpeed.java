package model.status.bonus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;
/**
 * A Status that boost player's speed of a certain amount.
 */
public class BonusSpeed extends Status {
	
	
	public BonusSpeed () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
		setStatusName(StatusEnum.BonusSpeed);
		setBoostFactor(3/2);
		
		setEffect(() -> getPlayer().setSpeed((Integer)getPlayer().getSpeed() * getBoostFactor()));
		setRemoveEffect(() -> getPlayer().setSpeed((Integer)getPlayer().getSpeed() * 1/getBoostFactor()));
		setDuration(10); 
	}
	
}
