package controller.gameEventController;

import javafx.scene.layout.AnchorPane;
import model.hud.EndGameGUI;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;

public class GameEventController implements IGameEventController {
    
    /*
     * HUD values
     */
    private final static int X_LAYOUT = 353;
    private final static int Y_LAYOUT = 3;
    private final static boolean TRUE = true;
    
    /*
     * Game Container reference and HUD elements
     */
    private AnchorPane gameContainer;
    private HUDPointsImpl hud;
    private HUDLifeImpl playerLives;

    /*
     * Gamestatus = this is important for gamecycle to continue
     */
    private boolean gameStatus;
    
    /**
     * Constructor.
     * @param Game container
     */
    public GameEventController(final AnchorPane container) {
        this.gameContainer = container;
        this.gameStatus = TRUE;
        
        createHUD(this.gameContainer, this.playerLives);
    }
    
    @Override
    public void endGame() {
        new EndGameGUI().end(this.checkPoints(), this.checkLevel());        
    }
    
    @Override
    public void createHUD(final AnchorPane gamePane, final HUDLifeImpl lives) {
        this.hud = new HUDPointsImpl();
        this.hud.setLayoutX(X_LAYOUT);
        this.hud.setLayoutY(Y_LAYOUT);
        gamePane.getChildren().add(this.hud);
        
        this.playerLives = new HUDLifeImpl(gamePane);
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
        return this.playerLives.getLifePoints();
    }
    
    @Override
    public int checkLevel() {
        return this.hud.getActualLevel();
    } 
}
