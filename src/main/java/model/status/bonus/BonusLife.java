package model.status.bonus;

import java.io.File;

import javafx.scene.image.Image;
import model.status.Status;
import model.status.StatusEnum;
/**
 * A Status that, below a certain maximum, add 1 bonus life to the player.
 */
public class BonusLife extends Status{
	
	private int maximumNumberLife = 4;
	
	public BonusLife () {
		super();
		setImage(new Image(new File("/src/main/resources/Images/"+"BonusSpeed.png").toURI().toString(),40,40,false,false));
		setStatusName(StatusEnum.BonusLife);
		
		setEffect(() -> {
			if (getPlayer().getLifePoints() < maximumNumberLife && getPlayer().getLifePoints() > 0)
				getPlayer().increaseLifePoints(1);});
		setRemoveEffect(() -> {});
		setDuration(0); 
	}
}
