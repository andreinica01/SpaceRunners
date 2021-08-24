package model.status;

import Utilities.Direction;
import Utilities.Parameters;
import model.EntityImpl;
import model.ship.SpaceShip;

/**
 * An entity that can apply, under certain condition, an effect (bonus or malus)
 * to the player. Any type of effect are temporary, each of them have a different
 * cooldown.
 */
public abstract class Status extends EntityImpl {

	private long coolDown; //in s
	private double boostFactor;

	private SpaceShip player;
	private Runnable effect;
	private Runnable removeEffect;
	private StatusEnum statusName;

	public Status() {
		setDirection(Direction.DOWN);
		setSpeed(Parameters.STATUS_SPEED);
		setImage(Parameters.randomStatusImage);
	}

	public long getCoolDown() {
		return coolDown;
	}

	protected void setCoolDown(long coolDown) {
		this.coolDown = coolDown;
	}

	public Runnable getEffect() {
		return effect;
	}

	protected void setEffect(Runnable effect) {
		this.effect = effect;
	}

	public Runnable getRemoveEffect() {
		return removeEffect;
	}

	protected void setRemoveEffect(Runnable removeEffect) {
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

	protected void setStatusName(StatusEnum statusName) {
		this.statusName = statusName;
	}

	protected void setBoostFactor (double value) {
		this.boostFactor = value;
	}

	public double getBoostFactor() {
		return this.boostFactor;
	}
}
