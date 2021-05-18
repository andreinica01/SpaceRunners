package model.bonus;

import Utilities.Direction;
import Utilities.Parameters;
import model.Entity;

public class BonusSpeed extends Bonus implements BonusEffect {
	
	public BonusSpeed () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);

	}
	
	@Override
	public void addBonus(Entity entity) {
		entity.setSpeed(10);
	}

}
