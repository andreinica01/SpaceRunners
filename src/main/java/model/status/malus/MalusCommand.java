package model.status.malus;

import java.util.Timer;

import Utilities.Parameters;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEffect;
import model.status.StatusTimer;

public class MalusCommand extends Status implements StatusEffect<SpaceShip> {
	
	public MalusCommand () {
		super();
		setImage(Parameters.bonusSpeedImage);
		getNode().setRotate(0);
	}

	@Override
	public void addBonus(SpaceShip entity, long duration) {
		entity.setInvertedCommand(true);
		Runnable x = () -> entity.setInvertedCommand(false);
		this.timer = new Timer();
		StatusTimer task = new StatusTimer(x, this.timer);
		this.timer.schedule(task, duration);
	}	
	
}
