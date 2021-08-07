package model.status;

import java.util.Timer;

import Utilities.Direction;
import model.EntityImpl;
import model.ship.SpaceShip;

public abstract class Status extends EntityImpl {
	
	private long time;
	protected long duration;
	public Timer timer;
	
	private SpaceShip player;
	private Runnable effect;
	private Runnable removeEffect;
	private StatusEnum statusName;
	
	public Status() {
		setDirection(Direction.DOWN);
		setSpeed(3);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
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
