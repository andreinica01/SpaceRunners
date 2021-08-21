package model.status.bonus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;
/**
 * A Status that, below a certain maximum, add 1 bonus life to the player.
 */
public class BonusLife extends Status{
	
	private int maximumNumberLife = 4;
	
	public BonusLife () {
		super();
		setImage(Parameters.bonusSpeedImage);
		setStatusName(StatusEnum.BonusLife);
		
		setEffect(() -> {
			if (getPlayer().getLifePoints() < maximumNumberLife && getPlayer().getLifePoints() > 0)
				getPlayer().increaseLifePoints(1);});
		setRemoveEffect(() -> {});
		setDuration(0); 
	}
}
