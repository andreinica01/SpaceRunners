package utilities;

import java.io.File;
import javafx.scene.image.Image;

public enum MagicEnumImage {

    /**
     * 
     */
    BULLET("/Images/bullet.png"),

    /**
     * 
     */
    BACKGROUND("/Images/stars2.png"),

    /**
     * 
     */
    PLAYER("/Images/playerShip.png"),

    /**
     * 
     */
    ENEMY("/Images/enemy.png"),

    /**
     * 
     */
    BOSS("/Images/bossShip.png");

    private Image value;

    /**
     * Constructor.
     * 
     * @param string
     */
    private MagicEnumImage(final String path) {
        this.value = new Image(getClass().getResourceAsStream(path));
    }

    /**
     * @return enum value.
     */
    public Image getImage() {
        return this.value;
    }
}
