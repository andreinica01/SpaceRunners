package GameController;

import FrameManager.FrameManager;
import GameField.GameField;
import Utilities.Direction;
import Utilities.InputCommand;
import Utilities.Vector2DImpl;
import GameObjects.Bullet;
import GameObjects.SpaceShip;
import InputController.InputControllerImpl;
import Utilities.*;

public class GameContollerImpl implements GameController {

    private GameField gamefield;
    private FrameManager frame;
    // private Set<Entity> enemies;

    private SpaceShip player;
    private InputControllerImpl inputController;

    public GameContollerImpl(GameField gamefield) {

        this.gamefield = gamefield;
        this.inputController = new InputControllerImpl();
        this.player = new SpaceShip();

        /* setup player info */
        this.player.setDimension(new Vector2DImpl<Number>(100, 100));
        this.player.setImage(Parameters.ImageFolder +"spaceship2.png");
        this.player.setSpeed(4);
        this.player.setDirection(Direction.NONE);
        this.player.setPosition(this.gamefield.getWidth().intValue()/2, this.gamefield.getHeight().intValue()-200);

        this.gamefield.setPlayer(this.player);

        this.frame = new FrameManager(this.gamefield);
        this.gamefield.setInputController(this.inputController);


        this.gamefield.setBackgroundImage("stars.png");

    }

    private void playerAttack()
    {
      
        Bullet x = new Bullet().bulletDamage(10);
       
        x.setPosition(this.player.getNode().getTranslateX()-180, this.player.getNode().getTranslateY()-200);

        x.setSpeed(10);
        
        

        this.gamefield.addBullet(x);
       
        
    }

    public void update() {

        InputCommand userCommand = this.inputController.getCommand();

        if (userCommand == InputCommand.GO_LEFT)
            this.player.setDirection(Direction.LEFT);
        else if (userCommand == InputCommand.GO_RIGHT)
            this.player.setDirection(Direction.RIGHT);
        else if (userCommand == InputCommand.ATTACK)
            playerAttack();

        frame.update();

    }

}
