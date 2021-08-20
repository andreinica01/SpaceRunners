package model.hud;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;

/**
 * This class defines how the HUD must look. It contains all of his
 * fields in order to let it work and every part of it that must be
 * displayed
 */
public class HUDModelImpl extends Label implements IHUDModel {
    
    /*
     * Measures
     */
    public final static int PREF_WIDTH = 130;
    public final static int PREF_HEIGHT = 50;
    
    /*
     * File System
     */

    public final static String RES_FOLDER = "src/main/resources/";
    public final static String PNG_FOLDER = RES_FOLDER + "Images/";
    public final static String FONT_PATH = "src/main/resources/Images/kenvector_future.ttf";
    public final static String POINTS_PATH = "src/main/resources/Images/info_label.png";
    
    /*
     * HUD structure
     */
    public final static Pos PREF_ALIGNEMENT = Pos.CENTER_LEFT;
    private final static double INSETS_MEASURES = 10;
    private final static boolean RATIO = false;
    private final static boolean SMOOTH = true;
    private final static BackgroundSize DEFAULT_SIZE = null;
    private final static String MATTER = "Points: ";
    
    /*
     * Values
     */
    private final static int FONT_SIZE = 15;
    private final static int ZERO = 0;
    private final static int POINTS_UP = 1;
    private final static int POINTS_DOWN = -5;
    private final static int FIVE = 5;
    private final static int MAX_LIFE_POINTS = 4;
    public final static int INITIAL_LIFE_POINTS = 3;
    private final static int LESS_LIVES_POSSIBLE = 1;
   
    /*
     * Control fields
     */
    private int lifePoints;
    private int level = ZERO;
    private int points;
    
    /**
     * Constructor
     */
    public HUDModelImpl() {
        
        setPrefWidth(PREF_WIDTH);
        setPrefHeight(PREF_HEIGHT);
        
        BackgroundImage backImage = new BackgroundImage(new Image(new File(PNG_FOLDER + "info_label.png").toURI().toString(), 
                                                            PREF_WIDTH, 
                                                            PREF_HEIGHT, 
                                                            RATIO, 
                                                            SMOOTH), 
                                                BackgroundRepeat.REPEAT, 
                                                BackgroundRepeat.REPEAT, 
                                                BackgroundPosition.DEFAULT, 
                                                DEFAULT_SIZE);
        setBackground(new Background(backImage));
        setPadding(new Insets(INSETS_MEASURES, 
                INSETS_MEASURES, 
                INSETS_MEASURES, 
                INSETS_MEASURES));

        
        
        setLabelFont();
        setAlignment(PREF_ALIGNEMENT);
        setText(MATTER + getPoints());
        pointsUp();
        this.lifePoints = INITIAL_LIFE_POINTS;

    }
    
    /*
     * Font loader
     */
    @Override
    public void setLabelFont() {
        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), FONT_SIZE));
    }
    
    /*
     * Getter
     */
    @Override
    public int getPoints() {
        return this.points;
    }
    
    @Override
    public int getActualLevel() {
        return this.level;
    }
    
    @Override
    public int getLifePoints() {
        return this.lifePoints;
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
        if(this.getPoints() < FIVE) {
            this.points = ZERO;
        } else {
            this.pointsSetter(POINTS_DOWN);
        }
    }
    
    @Override
    public void pointsSetter(final int value) {
        this.points += value;
    }
    
    @Override
    public void lifeUp() {
        if (this.getLifePoints() < MAX_LIFE_POINTS) {
            this.lifePoints++;
        }
    }
    
    @Override
    public void lifeDown() {
        if (this.getLifePoints() > LESS_LIVES_POSSIBLE) {
            this.lifePoints--;
        } else {
            this.lifePoints = ZERO; 
        }      
    }
    
    @Override
    public void levelUp() {
        this.level++; 
    }
}
