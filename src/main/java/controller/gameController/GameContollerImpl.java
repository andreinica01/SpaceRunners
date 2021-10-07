package controller.gameController;

import controller.enemyAI.EnemyAI;
import controller.frameManager.FrameManager;
import controller.gameEventController.GameEventController;
import controller.inputController.InputControllerImpl;
import controller.status.StatusController;
import java.io.IOException;
import java.util.Map;
import model.ship.PlayerSpaceShip;
import model.ship.SpaceShip;
import utilities.Direction;
import utilities.InputCommand;
import utilities.MagicEnumString;
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
        this.gamefield.setBackgroundImage(MagicEnumString.IMAGE_FOLDER.getValue() + "back.png");

        /* setup player info */
        this.player = new PlayerSpaceShip(this.gamefield);
        this.player.setPosition(this.gamefield.getWidth().intValue() / X_PLAYER_STABILIZER,
        		this.gamefield.getHeight().intValue() - Y_PLAYER_STABILIZER);
        this.gamefield.setPlayer(this.player);

        this.frame = new FrameManager(this.gamefield);
        this.gameEventController = new GameEventController(this.gamefield);
        this.aiController = new EnemyAI(this.gamefield, this.gameEventController);

        this.statusController = new StatusController(this.gamefield.getPlayer());
        this.gamefield.setStatusController(this.statusController);
    }

    @Override
    public final void update() {
        this.controlStates = this.inputController.getControlStates();
        this.player.setDirection(Direction.NONE);

        if (this.controlStates.get(InputCommand.LEFT)) {
            if (this.player.isInvertedCommand()) {
                this.player.setDirection(Direction.RIGHT);
            } else {
                this.player.setDirection(Direction.LEFT);
            }
        }

        if (this.controlStates.get(InputCommand.RIGHT)) {
            if (this.player.isInvertedCommand()) {
                this.player.setDirection(Direction.LEFT);
            } else {
                this.player.setDirection(Direction.RIGHT);
            }
        }

        if (this.controlStates.get(InputCommand.ATTACK) && this.player.getCanFire()) {
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
            try {
				this.gamefield.getGameManager().getSceneManager().getRanking()
				.addToMap(this.gamefield.getGameManager().getPlayerName(), this.gameEventController.checkPoints());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    /**
     * @return the Status controller.
     */
    public StatusController getStatusController() {
        return this.statusController;
    }

    /**
     * @return gameFsield reference.
     */
    public GameField getGameField() {
    	return this.gamefield;
    }

    /**
     * Sets the InputController.
     * @param inputController
     */
    public final void setInputController(final InputControllerImpl inputController) {
    	this.inputController = inputController;
    	this.inputController.changeScene(this.player.getNode().getScene());
    }
}
