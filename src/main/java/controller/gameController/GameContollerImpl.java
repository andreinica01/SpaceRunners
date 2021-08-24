package controller.gameController;

import java.util.Map;
import Utilities.Direction;
import Utilities.InputCommand;
import Utilities.Parameters;
import controller.enemyAI.enemyAI;
import controller.frameManager.FrameManager;
import controller.gameEventController.GameEventController;
import controller.inputController.InputControllerImpl;
import model.bullet.Bullet;
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
    private enemyAI AIController;
    private Map<InputCommand, Boolean> controlStates;

    public GameContollerImpl(GameField gamefield) {

        this.gamefield = gamefield;
        
        /* setup player info */
        this.player = new PlayerSpaceShip();
        this.player.setPosition(this.gamefield.getWidth().intValue() / 2, this.gamefield.getHeight().intValue() - 200);

        this.gamefield.setPlayer(this.player);
        this.frame = new FrameManager(this.gamefield);
        this.gamefield.setInputController(this.inputController);

        this.gamefield.setBackgroundImage(Parameters.ImageFolder + "back.png");
        this.inputController = new InputControllerImpl(this.player.getNode().getScene(), this.player);
        this.AIController = new enemyAI(this.gamefield);
        
        /*
         * HUD and game conditions setup
         */
        this.gameEventController = new GameEventController(this.gamefield.getGameContainer(), this.gamefield);
    }

    public void update() {

        controlStates = this.inputController.getControlStates();

        if (controlStates.get(InputCommand.GO_LEFT))
            if (!this.player.isInvertedCommand())
                this.player.setDirection(Direction.LEFT);
            else
                this.player.setDirection(Direction.RIGHT);

        if (controlStates.get(InputCommand.GO_RIGHT))
            if (!this.player.isInvertedCommand())
                this.player.setDirection(Direction.RIGHT);
            else
                this.player.setDirection(Direction.LEFT);

        if (controlStates.get(InputCommand.NONE))
            this.player.setDirection(Direction.NONE);

        if (controlStates.get(InputCommand.ATTACK)) {
            playerAttack();
        }

        /*
         * Collision and update system
         */
        this.removeAttacked();
        this.gameEventController.getCollisionEngine().playerShipCollision();
        this.gameEventController.getCollisionEngine().playerCollsionBorders();
        this.AIController.update();
        
        if(this.gameEventController.checkGameStatus() == false) {
            System.exit(0);
        }
        
        frame.update();
    }

    private void playerAttack() {

        Bullet x = new Bullet().bulletDamage(10);

        x.setPosition(this.player.getNode().getTranslateX() - 190, this.player.getNode().getTranslateY() - 200);
        x.setSpeed(10);

        this.gamefield.addBullet(x);
        this.gamefield.getSoundManager().playBulletSound();
    }

    private void removeAttacked() {
        this.gameEventController.getCollisionEngine().removeCollidedShips();
    }
}
