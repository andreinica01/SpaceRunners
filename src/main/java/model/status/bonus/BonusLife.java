package model.status.bonus;

import Utilities.Parameters;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEffect;

public class BonusLife extends Status implements StatusEffect<SpaceShip>{
	
	
	public BonusLife () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
	}
	
	@Override
	public void addBonus(SpaceShip entity, long duration) {
		if (entity.getLifePoints() < 4)
			entity.setLifePoints(entity.getLifePoints() + 1);
	}



}
