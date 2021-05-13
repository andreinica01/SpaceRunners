package GameController;

import FrameManager.FrameManager;
import GameField.GameField;
import Utilities.Direction;
import Utilities.Vector2DImpl;
import GameObjects.SpaceShip;
import InputController.InputControllerImpl;


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
            this.player.setImage("src/main/resources/Images/spaceship2.png");
            this.player.setSpeed(1);
            this.player.setDirection(Direction.RIGHT);
            this.player.setPosition(0, 10);
            

        this.gamefield.setPlayer(this.player); 

        this.frame = new FrameManager(this.gamefield);
        this.gamefield.setInputController(this.inputController);
        
        

    }

    public void update() {

        frame.update();
        this.inputController.isLeft();


    }

}
