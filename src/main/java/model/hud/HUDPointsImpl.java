package model.hud;

import Utilities.HUDParameters;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * This class defines how the points HUD must look.
 */
public class HUDPointsImpl extends Label implements IHUDPoints {

    /*
     * HUD structure
     */

    private static final int PREF_WIDTH = 130;
    private static final int PREF_HEIGHT = 50;
    private static final String MATTER = "Points: ";
    private static final int POINTS_UP = 1;
    private static final int POINTS_DOWN = -5;

    /*
     * Control field
     */
    private int points;

    /*
     * Constructor
     */
    public HUDPointsImpl() {
        this.setPrefWidth(PREF_WIDTH);
        this.setPrefHeight(PREF_HEIGHT);

        this.points = HUDParameters.ZERO;

        this.setAlignment(HUDParameters.PREF_ALIGNEMENT);
        this.setText(MATTER + this.getPoints());
        this.setFont(new Font(HUDParameters.FONT, HUDParameters.FONT_SIZE_999));
        this.setTextFill(Paint.valueOf("yellow"));
    }

    /*
     * Getter
     */
    @Override
    public int getPoints() {
        return this.points;
    }

    /*
     * Setter
     */
    @Override
    public void pointsUp() {
        this.pointsSetter(POINTS_UP);
    }

    @Override
    public void pointsDown() {
        if (this.getPoints() < HUDParameters.FIVE) {
            this.pointsSetter(-this.getPoints());
        } else {
            this.pointsSetter(POINTS_DOWN);
        }
    }

    @Override
    public void pointsSetter(final int value) {
        this.points += value;
        this.setText(MATTER + this.getPoints());
        this.fontStabilizer();
    }

    @Override
    public void fontStabilizer() {
        
        if(this.getPoints() > HUDParameters.FONT_TOLERANCE) {
            this.setFont(new Font(HUDParameters.FONT, HUDParameters.FONT_SIZE_9999));
        } else {
            this.setFont(new Font(HUDParameters.FONT, HUDParameters.FONT_SIZE_999));
        }
    }
}