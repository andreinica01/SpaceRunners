package model.status;

import Utilities.Direction;
import model.EntityImpl;

public abstract class Status extends EntityImpl {
	
	public Status() {
		setDirection(Direction.DOWN);
		setSpeed(3);
	}
}
