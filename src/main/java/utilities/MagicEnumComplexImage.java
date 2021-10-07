package utilities;

import java.io.File;

import javafx.scene.image.Image;

public enum MagicEnumComplexImage {
	/**
	 * 
	 */
	LIFE(new Image(new File(MagicEnumString.LIFE_PATH.getValue()).toURI().toString(), 
		50, 50, false, true)),

	/**
	 * 
	 */
	BONUS_LIFE(new Image(new File(MagicEnumString.BONUS_LIFE_PATH.getValue()).toURI().toString(), 
			50, 50, false, true)),

	/**
	 * 
	 */
	BONUS_SPEED(new Image(new File(MagicEnumString.BONUS_SPEED_PATH.getValue()).toURI().toString(), 
			50, 50, true, true)),

	/**
	 * 
	 */
	MALUS_COMMAND(new Image(new File(MagicEnumString.MALUS_COMMAND_PATH.getValue()).toURI().toString(), 
			50, 50, true, true)),

	/**
	 * 
	 */
	MALUS_FIRE(new Image(new File(MagicEnumString.MALUS_FIRE_PATH.getValue()).toURI().toString(), 
			50, 50, true, true)),

	/**
	 * 
	 */
	MALUS_SPEED(new Image(new File(MagicEnumString.MALUS_SPEED_PATH.getValue()).toURI().toString(), 
			50, 50, true, true)),

	/**
	 * 
	 */
	RANDOM_STATUS(new Image(new File(MagicEnumString.RANDOM_STATUS_PATH.getValue()).toURI().toString(), 
			50, 50, true, true));
	
	private Image value;

	/**
	 * Constructor.
	 * @param value
	 */
	private MagicEnumComplexImage(final Image value) {
		this.value = value;
	}

	/**
	 * @return enum value.
	 */
	public Image getImage() {
		return this.value;
	}
}
