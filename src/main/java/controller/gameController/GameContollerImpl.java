package controller.gameController;

import Utilities.Direction;
import Utilities.InputCommand;
import Utilities.Parameters;
import Utilities.Vector2DImpl;
import controller.enemyAI.enemyAI;
import controller.frameManager.FrameManager;
import controller.inputController.InputControllerImpl;
import model.bonus.BonusSpeed;
import model.bullet.Bullet;
import model.ship.SpaceShip;
import view.SoundManager.SoundManager;
import view.gameField.GameField;

public class GameContollerImpl implements GameController {

    private GameField gamefield;
    private FrameManager frame;
    InputCommand userCommand;
    Parameters param;
    // private Set<Entity> enemies;

    private SpaceShip player;
    private InputControllerImpl inputController;
    private enemyAI AIController;

    public GameContollerImpl(GameField gamefield) {

        this.gamefield = gamefield;
        this.inputController = new InputControllerImpl();

        this.player = new SpaceShip();

        param = new Parameters();
        this.player.setDimension(new Vector2DImpl<Number>(100, 100));
        this.player.setImage(Parameters.playerImage);
        this.player.setSpeed(4);
        this.player.setDirection(Direction.NONE);
        this.player.setPosition(this.gamefield.getWidth().intValue() / 2,
        						this.gamefield.getHeight().intValue() - 200);
        /* setup player info */

        this.gamefield.setPlayer(this.player);

        this.frame = new FrameManager(this.gamefield);
        this.gamefield.setInputController(this.inputController);

        this.gamefield.setBackgroundImage(Parameters.ImageFolder + "back.png");

        this.AIController = new enemyAI(this.gamefield);
     
    }

    private void playerAttack() {

        Bullet x = new Bullet().bulletDamage(10);

        x.setPosition(this.player.getNode().getTranslateX() - 190, this.player.getNode().getTranslateY() - 200);

        x.setSpeed(10);

        this.gamefield.addBullet(x);

        SoundManager.playBulletSound();

    }

    public void update() {

        userCommand = this.inputController.getCommand();

        if (userCommand == InputCommand.GO_LEFT) {
            this.player.setDirection(Direction.LEFT);

        } else if (userCommand == InputCommand.GO_RIGHT) {
            this.player.setDirection(Direction.RIGHT);

        } else
        	this.player.setDirection(Direction.NONE);
        
        if (userCommand == InputCommand.ATTACK) 
        	playerAttack();

        this.AIController.update();
        frame.update();

    }

}
