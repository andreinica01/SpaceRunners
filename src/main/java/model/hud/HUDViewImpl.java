package model.hud;

import Utilities.Parameters;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * This class defines our HUD for the game, structuring it's point system and reporting the
 * game conditions.
 */
public class HUDViewImpl extends AbstractHUD implements HUDView {
    
    private final static String PATH_STRING = Parameters.ImageFolder+"info_label.png";
    /*
    * Player gain 1 point for each kill.
    */
   private final static int UP = 1;
   
   /*
    * Player loses 5 point for each hit taken.
    */
   private final static int DOWN = -5;
   
   /*
    * Random values.
    */
   private final static int FIVE_VALUE = 5;
   private final static int ZERO_VALUE = 0;
   private final static int MAX_LIFE_POINTS = 4;
   public final static int INITIAL_LIFE_POINTS = 3;
   private final static int LESS_LIVES_POSSIBLE = 1;
   private final static double REQUESTED_WIDTH = 130;
   private final static double REQUESTED_HEIGHT = 50;
   private final static double INSETS_MEASURES = 10;
   private final static boolean RATIO = false;
   private final static boolean SMOOTH = false;
   private final static BackgroundSize DEFAULT_SIZE = null;
   
   /*
    * HPs.
    */
   private int lifePoints;

    /**
     * Constructor.
     * @param matter of the HUD.
     */
    public HUDViewImpl(final String matter) {
        
        /*
         * Dimensions.
         */
        setPrefWidth(AbstractHUD.PREF_WIDTH);
        setPrefHeight(AbstractHUD.PREF_HEIGHT);
        
        /*
         * BackGround.
         */
        BackgroundImage backImage = new BackgroundImage(new Image(PATH_STRING, 
                                                                    REQUESTED_WIDTH, 
                                                                    REQUESTED_HEIGHT, 
                                                                    RATIO, 
                                                                    SMOOTH), 
                                                        BackgroundRepeat.REPEAT, 
                                                        BackgroundRepeat.REPEAT, 
                                                        BackgroundPosition.DEFAULT, 
                                                        DEFAULT_SIZE);
        setBackground(new Background(backImage));
        
        /*
         * Alignement.
         */
        setAlignment(AbstractHUD.PREF_ALIGNEMENT);
        
        /*
         * Represent the amount of padding in pixels to add to the side 
         * of a BoundingBox when setting the camera of a map.
         */
        setPadding(new Insets(INSETS_MEASURES, 
                            INSETS_MEASURES, 
                            INSETS_MEASURES, 
                            INSETS_MEASURES));
        
        /*
         * Font.
         */
        super.setLabelFont();
        
        /*
         * Matter.
         */
        setText(matter + super.getPoints());
        
        /*
         * Game starting condition is always true, starting life points are always 3.
         */
        super.setGameCondition(true);
        super.increaseLevel();
        this.lifePoints = INITIAL_LIFE_POINTS;
    }
    
    @Override
    public void pointsUp() {
        super.pointSetter(UP);
    }

    @Override
    public void pointsDown() {
        if(super.getPoints() < FIVE_VALUE) {
            super.pointSetter(ZERO_VALUE);
        } else {
            super.pointSetter(DOWN);
        }
    }
    
    @Override
    public void levelUp() {
        super.increaseLevel();
    }
    
    @Override
    public int getLevel() {
        return super.getActualLevel();
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
            this.lifePoints = ZERO_VALUE;
           super.setGameCondition(false); 
        }      
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }
}
