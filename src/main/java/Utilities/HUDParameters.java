package Utilities;

import javafx.geometry.Pos;
import javafx.scene.text.Font;

/**
 * This class contains various HUD parameters in order to give the code a better
 * look.
 */
public class HUDParameters {

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;

    public static final String PNG_FOLDER = Parameters.ImageFolder;

    public static final int INSETS_MEASURES = 10;
    public static final int FONT_SIZE_999 = 18;
    public static final int FONT_SIZE_9999 = 16;
    public static final Font FONT_9999 = new Font(HUDParameters.FONT, HUDParameters.FONT_SIZE_9999);
    public static final Font FONT_999 = new Font(HUDParameters.FONT, HUDParameters.FONT_SIZE_999);
    public static final int FONT_TOLERANCE = 999;
    public static final int MAX_LIFE_POINTS = 4;
    public static final int TOTAL_BONUS = 5;
    public static final int VIEW_ORDER = -51;

    public static final boolean RATIO = false;
    public static final boolean SMOOTH = true;
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    public static final String FONT = "Verdana";

    public static final Pos PREF_ALIGNEMENT = Pos.CENTER_LEFT;
}
