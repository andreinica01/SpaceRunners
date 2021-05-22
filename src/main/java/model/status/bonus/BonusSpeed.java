package model.status.bonus;

import Utilities.Parameters;
import model.Entity;
import model.status.Status;
import model.status.StatusEffect;

public class BonusSpeed extends Status implements StatusEffect<Entity> {
	
	public BonusSpeed () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
	}
	
	@Override
	public void addBonus(Entity entity, long duration) {
		entity.setSpeed(10);
	}



}
