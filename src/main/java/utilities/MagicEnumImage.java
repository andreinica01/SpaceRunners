package utilities;

import java.io.File;
import javafx.scene.image.Image;

public enum MagicEnumImage {

	/**
	 * 
	 */
	BULLET(new Image(new File(MagicEnumString.BULLET_PATH.getValue()).toURI().toString())),

	/**
	 * 
	 */
	BACKGROUND(new Image(new File(MagicEnumString.BACKGROUND_PATH.getValue()).toURI().toString())),

	/**
	 * 
	 */
	PLAYER(new Image(new File(MagicEnumString.PLAYER_PATH.getValue()).toURI().toString())),

	/**
	 * 
	 */
	ENEMY(new Image(new File(MagicEnumString.ENEMY_PATH.getValue()).toURI().toString())),

	/**
	 * 
	 */
	BOSS(new Image(new File(MagicEnumString.BOSS_PATH.getValue()).toURI().toString()));
	
	private Image value;

	/**
	 * Constructor.
	 * @param value
	 */
	private MagicEnumImage(final Image value) {
		this.value = value;
	}

	/**
	 * @return enum value.
	 */
	public Image getImage() {
		return this.value;
	}
}
