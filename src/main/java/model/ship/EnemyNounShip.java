package model.ship;

import utilities.Direction;
import utilities.Parameters;
import view.gameField.GameField;

public class EnemyNounShip extends EnemyShip {

    /**
     * Constructor.
     * @param gamefield
     */
    public EnemyNounShip(final GameField gamefield) {
        super(gamefield);
        this.setImage(Parameters.enemyImage);
        this.setDirection(Direction.DOWN);
        this.setSpeed(10);
        this.getNode().setScaleX(0.5);
        this.getNode().setScaleY(0.5);
    }
}
