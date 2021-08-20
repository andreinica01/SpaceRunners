package model.status.bonus;

import Utilities.Parameters;
import javafx.scene.image.Image;
import model.status.Status;
import model.status.StatusEnum;
import static Utilities.Parameters.*;
/**
 * A Status that, below a certain maximum, add 1 bonus life to the player.
 */
public class BonusLife extends Status{
	
	private int maximumNumberLife = 4;
	
	public BonusLife () {
		super();
		Image v = Parameters.getlifePointsImage();
		setImage(Parameters.bonusSpeedImage); //Si rompe tutto qui.

		setStatusName(StatusEnum.BonusLife);
		
		setEffect(() -> {
			if (getPlayer().getLifePoints() < maximumNumberLife && getPlayer().getLifePoints() > 0)
				getPlayer().increaseLifePoints((int)1);});
		setRemoveEffect(() -> {});
		setDuration(0); 
	}
}
