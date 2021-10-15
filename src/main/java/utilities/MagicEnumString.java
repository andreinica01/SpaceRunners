package utilities;

public enum MagicEnumString {

    /**
     * 
     */
    FONT("Verdana"),

    /**
     * 
     */
    RESOURCE_FOLDER("src/main/resources/"),

    /**
     * 
     */
    IMAGE_FOLDER(RESOURCE_FOLDER.getValue() + "Images/"),

    /**
     * 
     */
    SOUND_FOLDER(RESOURCE_FOLDER.getValue() + "Sounds/"),

    /**
     * 
     */
    BULLET_PATH(IMAGE_FOLDER.getValue() + "bullet.png"),

    /**
     * 
     */
    BACKGROUND_PATH(IMAGE_FOLDER.getValue() + "stars2.png"),

    /**
     * 
     */
    PLAYER_PATH(IMAGE_FOLDER.getValue() + "playerShip.png"),

    /**
     * 
     */
    ENEMY_PATH(IMAGE_FOLDER.getValue() + "enemy.png"),

    /**
     * 
     */
    LIFE_PATH(IMAGE_FOLDER.getValue() + "life.png"),

    /**
     * 
     */
    BONUS_LIFE_PATH(IMAGE_FOLDER.getValue() + "bonus0.png"),

    /**
     * 
     */
    BONUS_SPEED_PATH(IMAGE_FOLDER.getValue() + "bonus1.png"),

    /**
     * 
     */
    MALUS_COMMAND_PATH(IMAGE_FOLDER.getValue() + "bonus2.png"),

    /**
     * 
     */
    MALUS_FIRE_PATH(IMAGE_FOLDER.getValue() + "bonus3.png"),

    /**
     * 
     */
    MALUS_SPEED_PATH(IMAGE_FOLDER.getValue() + "bonus4.png"),

    /**
     * 
     */
    RANDOM_STATUS_PATH(IMAGE_FOLDER.getValue() + "randomStatus.png"),

    /**
     * 
     */
    BOSS_PATH(IMAGE_FOLDER.getValue() + "bossShip.png");

    private String value;

    /**
     * Constructor.
     * 
     * @param value
     */
    private MagicEnumString(final String value) {
        this.value = value;
    }

    /**
     * @return enum value.
     */
    public String getValue() {
        return this.value;
    }
}
