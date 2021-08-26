package controller.gameController;

import Utilities.Direction;
import Utilities.InputCommand;
import Utilities.Parameters;
import controller.enemyAI.enemyAI;
import controller.frameManager.FrameManager;
import controller.gameEventController.GameEventController;
import controller.inputController.InputControllerImpl;
import controller.status.StatusController;
import java.util.Map;
import model.ship.PlayerSpaceShip;
import model.ship.SpaceShip;
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
  private final enemyAI AIController;
  private Map<InputCommand, Boolean> controlStates;

  public GameContollerImpl(GameField gamefield) {
    this.gamefield = gamefield;

    /* setup player info */
    this.player = new PlayerSpaceShip(this.gamefield);
    this.player.setPosition(
        this.gamefield.getWidth().intValue() / 2, this.gamefield.getHeight().intValue() - 220);

    this.gamefield.setPlayer(this.player);
    this.frame = new FrameManager(this.gamefield);

    this.gamefield.setBackgroundImage(Parameters.ImageFolder + "back.png");
    this.inputController = new InputControllerImpl(this.player.getNode().getScene(), this.player);

    /*
     * HUD and game conditions setup
     */
    this.gameEventController = new GameEventController(this.gamefield);
    this.AIController = new enemyAI(this.gamefield, this.gameEventController);

    this.statusController = new StatusController(this.gamefield.getPlayer());
    this.gamefield.setStatusController(this.statusController);
  }

  public void update() {
    this.controlStates = this.inputController.getControlStates();

    if (this.controlStates.get(InputCommand.GO_LEFT))
      if (!this.player.isInvertedCommand()) this.player.setDirection(Direction.LEFT);
      else this.player.setDirection(Direction.RIGHT);

    if (this.controlStates.get(InputCommand.GO_RIGHT))
      if (!this.player.isInvertedCommand()) this.player.setDirection(Direction.RIGHT);
      else this.player.setDirection(Direction.LEFT);

    if (this.controlStates.get(InputCommand.NONE)) {
      this.player.setDirection(Direction.NONE);
    }

    if (controlStates.get(InputCommand.ATTACK)) {
      this.player.attack();
    }

    /*
     * Collision and update system
     */
    this.gameEventController.getCollisionEngine().update();
    this.AIController.update();

    if (!this.gameEventController.checkGameStatus()) {
        this.gamefield.getSoundManager().playDeathSound();
        System.exit(0);
    }

    frame.update();
  }

  public StatusController getStatusController() {
    return statusController;
  }
}
