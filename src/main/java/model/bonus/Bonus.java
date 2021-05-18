package model.bonus;

import Utilities.Direction;
import model.EntityImpl;

public class Bonus extends EntityImpl {
	
	public Bonus() {
		setDirection(Direction.DOWN);
		setSpeed(3);
	}
}
