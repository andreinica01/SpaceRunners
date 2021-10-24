package utilities;

import javafx.scene.image.Image;

public enum MagicEnumComplexImage {
    /**
     * 
     */
    LIFE("/Images/life.png"),

    /**
     * 
     */
    BONUS_LIFE("/Images/bonus0.png"),

    /**
     * 
     */
    BONUS_SPEED("/Images/bonus1.png"),

    /**
     * 
     */
    MALUS_COMMAND("/Images/bonus2.png"),

    /**
     * 
     */
    MALUS_FIRE("/Images/bonus3.png"),

    /**
     * 
     */
    MALUS_SPEED("/Images/bonus4.png"),

    /**
     * 
     */
    RANDOM_STATUS("/Images/randomStatus.png");

    private Image value;

    /**
     * Constructor.
     * 
     * @param path
     */
    private MagicEnumComplexImage(final String path) {
        double fifty = 50;
        this.value = new Image(getClass().getResourceAsStream(path), fifty, fifty, true, true);
    }

    /**
     * @return enum value.
     */
    public Image getImage() {
        return this.value;
    }
}
