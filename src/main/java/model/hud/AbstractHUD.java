package model.hud;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * This abstract class provides an alpha version of a possible hud defining it's dimensions,
 * points system, alignement, font.
 * it can be used for differents kind of hud, that's because it implements it's core design
 * leaving more choice to the user
 */
public abstract class AbstractHUD extends Label implements HUDDesign {

    public final static int PREF_WIDTH = 130;
    public final static int PREF_HEIGHT = 50;
    public final static String FONT_PATH = "src/main/resources/Images/kenvector_future.ttf";
    public final static Pos PREF_ALIGNEMENT = Pos.CENTER_LEFT;
    private final static int FONT_SIZE = 15;

    private int points;
    private boolean gameConditionValue;

    @Override
    public abstract void pointsUp();

    @Override
    public abstract void pointsDown();
    
    @Override
    public void setLabelFont() {
        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), FONT_SIZE));
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void pointSetter(final int value) {
        this.points += value;
    }
    
    @Override
    public boolean getGameCondition() {
        return this.gameConditionValue;
    }
    
    @Override
    public void setGameCondition(final boolean value) {
        this.gameConditionValue = value;
    }
}
