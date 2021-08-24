package controller.gameEventController;

import controller.collisionEngine.PhysicsEngine;
import controller.collisionEngine.PhysicsEngineImpl;
import javafx.scene.layout.AnchorPane;
import model.hud.EndGameGUI;
//import model.hud.HUDBonusImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDParameters;
import model.hud.HUDPointsImpl;
import model.status.Status;
import view.gameField.GameField;

public class GameEventController implements IGameEventController {
    
    /*
     * HUD values
     */
    private final static int X_LAYOUT = 353;
    private final static int Y_LAYOUT = 3;
    
    /*
     * Game Container reference and HUD elements
     */
    private AnchorPane gameContainer;
    private HUDPointsImpl hud;
    private HUDLifeImpl playerLives;
    private PhysicsEngineImpl collisionEngine;
    //private HUDBonusImpl bonusHUD;
    private GameField gameField;
    
    /**
     * Constructor.
     * @param Game container
     */
    public GameEventController(final AnchorPane container, final GameField gameField) {
        this.gameContainer = container;
        this.gameField = gameField;
        
        createHUD(this.gameContainer);
    }
    
    @Override
    public void endGame() {
        //new EndGameGUI().end(this.checkPoints());        
    }
    
    @Override
    public void createHUD(final AnchorPane gamePane) {
        
        /*
         * Points HUD
         */
        this.hud = new HUDPointsImpl();
        this.hud.setLayoutX(X_LAYOUT);
        this.hud.setLayoutY(Y_LAYOUT);
        gamePane.getChildren().add(this.hud);
        this.hud.setViewOrder(HUDParameters.VIEW_ORDER);
        
        /*
         * Lives HUD
         */
        this.playerLives = new HUDLifeImpl(gamePane);
        
        /*
         * PowerUP HUD
         */
        //this.bonusHUD = new HUDBonusImpl(gamePane);
        
        /*
         * Collision engine comes with HUD creation!
         */
        this.collisionEngine = new PhysicsEngineImpl(this.gameField, this.hud, this.playerLives);
    }
    
    @Override
    public boolean checkGameStatus() {
        return this.playerLives.getStatus();
    }
    
    @Override
    public int checkPoints() {
        return this.hud.getPoints();
    }
    
    @Override
    public int checkLives() {
        return this.playerLives.getLifePoints();
    }

    @Override
    public PhysicsEngine getCollisionEngine() {
        return this.collisionEngine;
    }

    @Override
    public Status getStatusCollected() {
        return null;
    }
}
