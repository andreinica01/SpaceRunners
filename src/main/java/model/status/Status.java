package model.status;

import Utilities.Direction;
import model.EntityImpl;
import model.ship.SpaceShip;

/**
 * An entity that can apply, under certain condition, an effect (bonus or malus) to the player.
 * Any type of States are temporary, each have a different cooldown.
 */
public abstract class Status extends EntityImpl {
	
	private long duration; //in seconds
	
	private SpaceShip player;
	private Runnable effect;
	private Runnable removeEffect;
	private StatusEnum statusName;
	
	public Status() {
		setDirection(Direction.DOWN);
		setSpeed(3);
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Runnable getEffect() {
		return effect;
	}

	public void setEffect(Runnable effect) {
		this.effect = effect;
	}

	public Runnable getRemoveEffect() {
		return removeEffect;
	}

	public void setRemoveEffect(Runnable removeEffect) {
		this.removeEffect = removeEffect;
	}

	public SpaceShip getPlayer() {
		return player;
	}

	public void setPlayer(SpaceShip player) {
		this.player = player;
	}

	public StatusEnum getStatusName() {
		return statusName;
	}

	public void setStatusName(StatusEnum statusName) {
		this.statusName = statusName;
	}
}
