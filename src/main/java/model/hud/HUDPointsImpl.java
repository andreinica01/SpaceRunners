package model.hud;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import utilities.HUDParameters;
import utilities.VariousMagicNumbers;

/**
 * This class defines how the points HUD must look.
 */
public class HUDPointsImpl extends Label implements IHUDPoints {

    /*
     * HUD structure
     */
    private static final int X_LAYOUT = 360;
    private static final int Y_LAYOUT = 20;
    private static final int POINTS_UP = 1;
    private static final int POINTS_DOWN = -5;
    private static final String MATTER = "Points: ";

    /*
     * Control field
     */
    private int points;

    /**
     * Constructor.
     */
    public HUDPointsImpl() {

        this.points = VariousMagicNumbers.ZERO;

        this.setLayoutX(X_LAYOUT);
        this.setLayoutY(Y_LAYOUT);
        this.setText(MATTER + this.getPoints());
        this.setFont(new Font(HUDParameters.FONT, HUDParameters.FONT_SIZE));
        this.setTextFill(Paint.valueOf("yellow"));
    }

    /*
     * Getter
     */
    @Override
    public final int getPoints() {
        return this.points;
    }

    /*
     * Setter
     */
    @Override
    public final void pointsUp() {
        if (this.getPoints() < HUDParameters.MAX_POINTS_POSSIBLE) {
            this.pointsSetter(POINTS_UP);
        }
    }

    @Override
    public final void pointsDown() {
        if (this.getPoints() < VariousMagicNumbers.FIVE) {
            this.pointsSetter(-this.getPoints());
        } else {
            this.pointsSetter(POINTS_DOWN);
        }
    }

    @Override
    public final void pointsSetter(final int value) {
        this.points += value;
        this.setText(MATTER + this.getPoints());
    }
}
