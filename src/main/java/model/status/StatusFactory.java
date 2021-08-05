package model.status;

import model.status.bonus.BonusLife;
import model.status.bonus.BonusSpeed;
import model.status.malus.MalusCommand;
import model.status.malus.MalusFire;
import model.status.malus.MalusSpeed;

public class StatusFactory {

	public StatusFactory() {
	}

	public Status createStatus(StatusEnum type) {
		switch (type) {
		case BonusLife:
			return new BonusLife();
		case BonusSpeed:
			return new BonusSpeed();
		case MalusCommand:
			return new MalusCommand();
		case MalusFire:
			return new MalusFire();
		case MalusSpeed:
			return new MalusSpeed();
		}
		return null;
	}
}
