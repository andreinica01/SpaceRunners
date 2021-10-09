package model.status;

import java.util.Random;

/**
 * Enum containing all Status type.
 */
public enum StatusEnum {
	/**
	 * 
	 */
	BonusLife,

	/**
	 * 
	 */
	BonusSpeed,

	/**
	 * 
	 */
	MalusCommand,

	/**
	 * 
	 */
	MalusFire,

	/**
	 * 
	 */
	MalusSpeed;

	/**
	 * Return a random Status type.
	 */
	public static StatusEnum getRandom() {
		return StatusEnum.values()[new Random().nextInt(StatusEnum.values().length)];
	}
}
