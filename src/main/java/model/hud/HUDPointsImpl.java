package model.hud;

import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;

/** This class defines how the points HUD must look. */
public class HUDPointsImpl extends Label implements IHUDPoints {

  /*
   * HUD structure
   */
  private static final double INSETS = HUDParameters.INSETS_MEASURES;
  private static final int PREF_WIDTH = 130;
  private static final int PREF_HEIGHT = 50;
  private static final BackgroundSize DEFAULT_SIZE = null;
  private static final String MATTER = "Points: ";
  private static final int POINTS_UP = 1;
  private static final int POINTS_DOWN = -5;

  /*
   * Control field
   */
  private int points;
  private BackgroundImage backImage;

  /*
   * Constructor
   */
  public HUDPointsImpl() {
    this.setPrefWidth(PREF_WIDTH);
    this.setPrefHeight(PREF_HEIGHT);

    /*
     * Background set
     */
    this.backImage =
        new BackgroundImage(
            new Image(
                new File(HUDParameters.PNG_FOLDER + "info_label.png").toURI().toString(),
                PREF_WIDTH,
                PREF_HEIGHT,
                HUDParameters.RATIO,
                HUDParameters.SMOOTH),
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT,
            DEFAULT_SIZE);

    this.setBackground(new Background(this.backImage));

    this.setPadding(new Insets(INSETS, INSETS, INSETS, INSETS));

    this.points = HUDParameters.ZERO;

    this.setAlignment(HUDParameters.PREF_ALIGNEMENT);

    this.setText(MATTER + this.getPoints());

    this.setFont(new Font(HUDParameters.FONT, HUDParameters.FONT_SIZE));
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
  }
}
