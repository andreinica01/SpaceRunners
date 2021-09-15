package utilities;

import javafx.geometry.Pos;

/**
 * This class contains various HUD parameters in order to give the code a better
 * look.
 */
public final class HUDParameters {

	private HUDParameters() { }

    public static final String PNG_FOLDER = Parameters.ImageFolder;

    public static final int INSETS_MEASURES = 10;
    public static final int FONT_SIZE = 18;
    public static final int MAX_POINTS_POSSIBLE = 999;
    public static final int MAX_LIFE_POINTS = 4;
    public static final int TOTAL_BONUS = 5;
    public static final int VIEW_ORDER = -51;
    public static final int DIMENSION = 50;

    public static final double BOOST = (double) 3 / 2;
    public static final double SLOW = (double) 2 / 3;

    public static final boolean RATIO = false;
    public static final boolean SMOOTH = true;

    public static final String FONT = "Verdana";

    public static final Pos PREF_ALIGNEMENT = Pos.CENTER_LEFT;
}
