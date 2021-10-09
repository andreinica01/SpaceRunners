package utilities;

public enum MagicEnumInt {
	/**
	 * 
	 */
	ZERO(0),

	/**
	 * 
	 */
	ONE(1),

	/**
	 * 
	 */
	TWO(2),

	/**
	 * 
	 */
	THREE(3),

	/**
	 * 
	 */
	FOUR(4),

	/**
	 * 
	 */
	FIVE(5),

	/**
	 * 
	 */
	SEVEN(6),

	/**
	 * 
	 */
	TEN(10),

	/**
	 * 
	 */
	THIRTY(30),

	/**
	 * 
	 */
	ONE_HUNDRED(100),

	/**
	 * 
	 */
	BULLET_VIEW_ORDER(-50),

	/**
	 * 
	 */
	BULLET_X_TRANSITION(22),

	/**
	 * 
	 */
	BULLET_Y_TRANSITION(83),

	/**
	 * 
	 */
	INSETS_MEASURES(10),

	/**
	 * 
	 */
	FONT_SIZE(18),

	/**
	 * 
	 */
	MAX_POINTS_POSSIBLE(999),

	/**
	 * 
	 */
	COMMON_VIEW_ORDER(-51),

	/**
	 * 
	 */
	COMMON_DIMENSION(50),

	/**
	 * 
	 */
	WIDTH(500),

	/**
	 * 
	 */
	HEIGHT(720),

	/**
	 * 
	 */
	STATUS_SPEED(6);

	private final int value;

	/**
	 * Constructor.
	 * 
	 * @param value
	 */
	private MagicEnumInt(final int value) {
		this.value = value;
	}

	/**
	 * @return enum value.
	 */
	public int getValue() {
		return this.value;
	}
}
