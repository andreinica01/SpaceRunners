package model.status.malus;

import Utilities.Parameters;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEffect;

public class MalusFire extends Status implements StatusEffect<SpaceShip> {

	public MalusFire () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
	}
	
	
	@Override
	public void addBonus(SpaceShip entity) {
		entity.setCanFire(false);
	}
	
}
