package model.hud;

import java.io.File;
import Utilities.Parameters;
import controller.gameEventController.GameEventController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HUDLifeImpl implements IHUDLife {

    /*
     * File System
     */
    public final static String PNG_FOLDER = Parameters.ImageFolder;
    
    /*
     * HUD structure
     */
    private final static int SPACING = 50;
    private final static int INITIAL_MALUS = 1;
    private final static int INITIAL_LIFE_POINTS = 3;
    private final static int LESS_LIVES_POSSIBLE = 1;
    public final static int MAX_LIFE_POINTS = 4;
    
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
            this.addLife(i);
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
            this.addLife(this.lifePoints);
            this.lifePoints++;
        }
    }
    
    @Override
    public void lifeDown() {
        if (this.getLifePoints() > LESS_LIVES_POSSIBLE) {
            this.lifePoints--;
            this.removeLife();
        } else {
            this.lifePoints = HUDPointsImpl.ZERO;
            //end game check
        }      
    }
    
    /**
     * Helper method used to add a life on the screen, it is combined with game logic
     * @param index
     */
    public void addLife(final int index) {
        this.lives[index] = new ImageView(new Image(new File(HUDPointsImpl.PNG_FOLDER + "spaceshipLife.png").toURI().toString(), SPACING, 
                SPACING, HUDPointsImpl.RATIO, HUDPointsImpl.SMOOTH));
        this.lives[index].setLayoutX(index * SPACING);
        this.lives[index].setLayoutY(HUDPointsImpl.FIVE);
        this.pane.getChildren().add(lives[index]);
        this.lives[index].setViewOrder(GameEventController.VIEW_ORDER);
    }
   
    /**
     * Helper method used to remove a life on the screen, it is combined with game logic
     */
    public void removeLife() {
        this.pane.getChildren().remove(lives[this.lifePoints]);
    }
}
