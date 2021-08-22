package model.hud;

import java.io.File;
import Utilities.Parameters;
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
public class HUDPointsImpl extends Label implements IHUDPoints {
    
    /*
     * File System
     */
    public final static String PNG_FOLDER = Parameters.ImageFolder;
    
    /*
     * HUD structure
     */
    private final static int PREF_WIDTH = 130;
    private final static int PREF_HEIGHT = 50;
    private final static Pos PREF_ALIGNEMENT = Pos.CENTER_LEFT;
    private final static double INSETS_MEASURES = 10;
    public final static boolean RATIO = false;
    public final static boolean SMOOTH = true;
    private final static BackgroundSize DEFAULT_SIZE = null;
    private final static String MATTER = "Points: ";
    private final static String FONT = "Verdana";
    private final static int FONT_SIZE = 20;
    public final static int ZERO = 0;
    private final static int POINTS_UP = 1;
    private final static int POINTS_DOWN = -5;
    public final static int FIVE = 5;
   
    /*
     * Control fields
     */
    private int level;
    private int points;
    
    /**
     * Constructor
     */
    public HUDPointsImpl() {
        
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

        setAlignment(PREF_ALIGNEMENT);
        setText(MATTER + getPoints());
        setFont(new Font(FONT, FONT_SIZE));
        
        this.level = POINTS_UP;
        this.points = ZERO;
        
        //decidere come gestire posizionamento display livello
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
    
    /*
     * Setter
     */
    @Override
    public void pointsUp() {
        this.pointsSetter(POINTS_UP);
        setText(MATTER + this.getPoints());
    }
    
    @Override
    public void pointsDown() {
        if(this.getPoints() < FIVE) {
            this.points = ZERO;
            setText(MATTER + this.getPoints());
        } else {
            this.pointsSetter(POINTS_DOWN);
            setText(MATTER + this.getPoints());
        }
    }
    
    @Override
    public void pointsSetter(final int value) {
        this.points += value;
    }
    
    @Override
    public void levelUp() {
        this.level++; 
    }
}