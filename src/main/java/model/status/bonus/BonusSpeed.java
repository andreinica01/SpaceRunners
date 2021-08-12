package model.status.bonus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;
/**
 * A Status that boost player's speed of a certain amount.
 */
public class BonusSpeed extends Status {
	
	private int boostFactor = 3/2;
	
	public BonusSpeed () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
		setStatusName(StatusEnum.BonusSpeed);
		
		setEffect(() -> getPlayer().setSpeed((Integer)getPlayer().getSpeed() * boostFactor));
		setRemoveEffect(() -> getPlayer().setSpeed((Integer)getPlayer().getSpeed() * 1/boostFactor));
		setDuration(10); 
	}
}
