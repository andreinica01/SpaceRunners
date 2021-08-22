package model.hud;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HUDLifeImpl implements IHUDLife {

    /*
     * File System
     */
    private final static String RES_FOLDER = "src/main/resources/";
    public final static String PNG_FOLDER = RES_FOLDER + "Images/";
    
    /*
     * HUD structure
     */
    private static final int ZERO = 0;
    public final static int MAX_LIFE_POINTS = 4;
    private final static int INITIAL_LIFE_POINTS = 3;
    private final static int LESS_LIVES_POSSIBLE = 1;
    private final static int INITIAL_MALUS = 1;
    
    /*
     * Control fields
     */
    private AnchorPane pane;
    private int lifePoints;
    private ImageView[] lives = new ImageView[MAX_LIFE_POINTS];
    
    public HUDLifeImpl(final AnchorPane gamePane) {
        
        this.pane = gamePane;
        this.lifePoints = INITIAL_LIFE_POINTS;
        
        for(int i = HUDPointsImpl.ZERO; i < lives.length - INITIAL_MALUS; i++) {
            addLife(i);
        }
    }
    
    /*
     * Getter
     */
    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }
    
    /*
     * Setter
     */
    @Override
    public void lifeUp() {
        if (this.getLifePoints() < MAX_LIFE_POINTS) {
            addLife(this.lifePoints);
            this.lifePoints++;
        }
    }
    
    @Override
    public void lifeDown() {
        if (this.getLifePoints() > LESS_LIVES_POSSIBLE) {
            this.lifePoints--;
            removeLife();
        } else {
            this.lifePoints = ZERO; 
        }      
    }
    
    /**
     * Helper method used to add a life on the screen, it is combined with game logic
     * @param index
     */
    public void addLife(final int index) {
        this.lives[index] = new ImageView(new Image(new File(HUDPointsImpl.PNG_FOLDER + "spaceshipLife.png").toURI().toString(), 50, 
                50, false, true));
        this.lives[index].setLayoutX(index * 50);
        this.lives[index].setLayoutY(5);
        this.pane.getChildren().add(lives[index]);
    }
   
    /**
     * Helper method used to remove a life on the screen, it is combined with game logic
     */
    public void removeLife() {
        this.lives[this.lifePoints] = null;
    }
}
