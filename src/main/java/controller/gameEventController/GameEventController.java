package controller.gameEventController;

import controller.collisionEngine.PhysicsEngine;
import controller.collisionEngine.PhysicsEngineImpl;
import javafx.scene.layout.AnchorPane;
import model.hud.EndGameGUI;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import view.gameField.GameField;

public class GameEventController implements IGameEventController {
    
    /*
     * HUD values
     */
    private final static int X_LAYOUT = 353;
    private final static int Y_LAYOUT = 3;
    public static final double VIEW_ORDER = -51;
    
    /*
     * Game Container reference and HUD elements
     */
    private AnchorPane gameContainer;
    private HUDPointsImpl hud;
    private HUDLifeImpl playerLives;
    public PhysicsEngineImpl collision;
    private GameField gameField;
    
    /**
     * Constructor.
     * @param Game container
     */
    public GameEventController(final AnchorPane container, final GameField gameField) {
        this.gameContainer = container;
        this.gameField = gameField;
        
        createHUD(this.gameContainer, this.playerLives);
    }
    
    @Override
    public void endGame() {
        new EndGameGUI().end(this.checkPoints(), this.checkLevel());        
    }
    
    @Override
    public void createHUD(final AnchorPane gamePane, final HUDLifeImpl lives) {
        /*
         * Points HUD
         */
        this.hud = new HUDPointsImpl();
        this.hud.setLayoutX(X_LAYOUT);
        this.hud.setLayoutY(Y_LAYOUT);
        gamePane.getChildren().add(this.hud);
        this.hud.setViewOrder(VIEW_ORDER);
        
        /*
         * Lives HUD
         */
        this.playerLives = new HUDLifeImpl(gamePane);
        
        /*
         * PowerUP HUD
         */
        
        /*
         * Collision engine comes with HUD creation!
         */
        this.collision = new PhysicsEngineImpl(this.gameField, this.hud, this.playerLives);
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
    public int checkLevel() {
        return this.hud.getActualLevel();
    } 

    @Override
    public PhysicsEngine getCollisionEngine() {
        return this.collision;
    }
}
