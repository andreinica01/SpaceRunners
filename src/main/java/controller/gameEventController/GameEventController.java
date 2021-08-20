package controller.gameEventController;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.hud.HUDModelImpl;
import controller.collisionEngine.*;

public class GameEventController implements IGameEventController {
    
    /*
     * HUD values
     */
    private final static int X_LAYOUT = 460;
    private final static int Y_LAYOUT = 20;
    
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
    
    /**
     * Constructor.
     * @param Game container
     */
    public GameEventController(final AnchorPane container) {
        this.gameContainer = container;
        
        this.hud = new HUDModelImpl();
        this.hud.setLayoutX(X_LAYOUT);
        this.hud.setLayoutY(Y_LAYOUT);
        //problem here! non so dove accodare il nodo
        //container.getChildren().add(this.hud);
    }
    @Override
    public void endGame() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void createHUD(AnchorPane gamePane) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean checkGameStatus() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public int checkPoints() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int checkLives() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int checkLevel() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
