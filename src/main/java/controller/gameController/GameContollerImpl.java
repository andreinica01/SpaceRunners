package controller.gameController;

import controller.enemyAI.EnemyAI; 
import controller.frameManager.FrameManager;
import controller.gameEventController.GameEventController;
import controller.inputController.InputControllerImpl;
import controller.status.StatusController;
import java.util.Map;
import model.ship.PlayerSpaceShip;
import model.ship.SpaceShip;
import utilities.Direction;
import utilities.InputCommand;
import utilities.Parameters;
import view.gameField.GameField;

public class GameContollerImpl implements GameController {

    /*
     * Fields
     */
    private GameField gamefield;
    private FrameManager frame;
    private GameEventController gameEventController;
    private SpaceShip player;
    private InputControllerImpl inputController;
    private StatusController statusController;
    private final EnemyAI aiController;
    private Map<InputCommand, Boolean> controlStates;

    private static final double X_PLAYER_STABILIZER = 3.5;
    private static final int Y_PLAYER_STABILIZER = 220;

    /**
     * Constructor.
     * @param gamefield.
     */
    public GameContollerImpl(final GameField gamefield) {
        this.gamefield = gamefield;
        this.gamefield.setBackgroundImage(Parameters.ImageFolder + "back.png");

        /* setup player info */
        this.player = new PlayerSpaceShip(this.gamefield);
        this.player.setPosition(this.gamefield.getWidth().intValue() / X_PLAYER_STABILIZER,
        		this.gamefield.getHeight().intValue() - Y_PLAYER_STABILIZER);
        this.gamefield.setPlayer(this.player);

        this.frame = new FrameManager(this.gamefield);
        this.inputController = new InputControllerImpl(this.player.getNode().getScene(), this.player);
        this.gameEventController = new GameEventController(this.gamefield);
        this.aiController = new EnemyAI(this.gamefield, this.gameEventController);

        this.statusController = new StatusController(this.gamefield.getPlayer());
        this.gamefield.setStatusController(this.statusController);
    }

    @Override
    public final void update() {
        this.controlStates = this.inputController.getControlStates();
        
        this.player.setDirection(Direction.NONE);

        if (this.controlStates.get(InputCommand.GO_LEFT)) {
            if (!this.player.isInvertedCommand()) {
                this.player.setDirection(Direction.LEFT);
            } else {
                this.player.setDirection(Direction.RIGHT);
            }
        }

        if (this.controlStates.get(InputCommand.GO_RIGHT)) {
            if (!this.player.isInvertedCommand()) {
                this.player.setDirection(Direction.RIGHT);
            } else {
                this.player.setDirection(Direction.LEFT);
            }
        }

        if (this.controlStates.get(InputCommand.ATTACK)) {
            this.player.attack();
        }

        /* Collision and update system */
        this.gameEventController.getCollisionEngine().update();
        this.aiController.update();
        this.frame.update();

        if (!this.gameEventController.checkGameStatus()) {
            this.gamefield.getSoundManager().playDeathSound();
            this.gameEventController.endGame(this.getGameField().getGameManager().getSceneManager());
            this.gamefield.getGameManager().stop();
        }
    }

    /**
     * @return the Status controller.
     */
    public StatusController getStatusController() {
        return this.statusController;
    }
    
    public GameField getGameField() {
    	return this.gamefield;
    }
}
