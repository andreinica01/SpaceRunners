package controller.gameEventController;
 
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.hud.EndGameGUI;
import model.hud.HUDModelImpl;
import controller.collisionEngine.*;

public class GameEventController implements IGameEventController {
    
    /*
     * HUD values
     */
    private final static int X_LAYOUT = 353;
    private final static int Y_LAYOUT = 3;
    
    /*
     * Game Container
     */
    private AnchorPane gameContainer;
    
    /*
     * HUD Elements
     */
    private HUDModelImpl hud;
    private ImageView[] playerLifes;
    //bonus

    /*
     * Other fields
     */
    private boolean gameStatus;
    
    /**
     * Constructor.
     * @param Game container
     */
    public GameEventController(final AnchorPane container) {
        this.gameContainer = container;
        
        createHUD(this.gameContainer);
    }
    
    @Override
    public void endGame() {
        new EndGameGUI().end(this.checkPoints(), this.checkLevel());        
    }
    
    @Override
    public void createHUD(final AnchorPane gamePane) {
        this.hud = new HUDModelImpl();
        this.hud.setLayoutX(X_LAYOUT);
        this.hud.setLayoutY(Y_LAYOUT);
        gamePane.getChildren().add(this.hud);
        //bisogna trovare il modo di metterlo di fronte
    }
    
    @Override
    public boolean checkGameStatus() {
        return this.gameStatus;
    }
    
    @Override
    public int checkPoints() {
        return this.hud.getPoints();
    }
    
    @Override
    public int checkLives() {
        return this.hud.getLifePoints();
    }
    
    @Override
    public int checkLevel() {
        return this.hud.getActualLevel();
    } 
}
