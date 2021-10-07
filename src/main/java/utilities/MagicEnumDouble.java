package utilities;

public enum MagicEnumDouble {
	/**
	 * 
	 */
	SIXTEEN(16),
	
	/**
	 * 
	 */
	PLAYER_SCALE(0.3),
	
	/**
	 * 
	 */
	BULLET_SCALE(0.08),
	
	/**
	 * 
	 */
	BOSS_SCALE(0.15),

	/**
	 * 
	 */
	BOOST((double) 3 / 2),

	/**
	 * 
	 */
	SLOW((double) 2 / 3);
	
	private final double value;

	/**
	 * Constructor.
	 * @param value
	 */
	private MagicEnumDouble(final double value) {
		this.value = value;
	}
	
	/**
	 * @return enum value.
	 */
	public double getValue() {
		return this.value;
	}
}
