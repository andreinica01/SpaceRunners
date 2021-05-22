package model.status;

import java.util.Timer;

import Utilities.Direction;
import model.EntityImpl;

public abstract class Status extends EntityImpl {
	
	private long time;
	public Timer timer;
	
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
}
