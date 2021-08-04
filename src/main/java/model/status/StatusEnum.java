package model.status;

import java.util.Random;

public enum StatusEnum {
	BonusLife,
	BonusSpeed,
	MalusCommand,
	MalusFire,
	MalusSpeed;
	
	/*
	 * Return a random element
	 */
	public static StatusEnum getRandom() {
		return StatusEnum.values()[new Random().nextInt(StatusEnum.values().length)];
	}
}
