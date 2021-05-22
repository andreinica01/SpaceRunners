package model.status.malus;

import Utilities.Parameters;
import model.Entity;
import model.status.Status;
import model.status.StatusEffect;

public class MalusSpeed extends Status implements StatusEffect<Entity> {

	public MalusSpeed () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
	}
	
	
	@Override
	public void addBonus(Entity entity, long duration) {
		entity.setSpeed(entity.getSpeed().intValue() * 1/3);
	}

}
