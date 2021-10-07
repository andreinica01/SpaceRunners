package model.ship;

import utilities.Direction;
import utilities.Parameters;
import utilities.VariousMagicNumbers;
import view.gameField.GameField;

public class BossShip extends EnemyShip {

    /**
     * Constructor.
     * @param gamefield
     */
    public BossShip(final GameField gamefield) {
        super(gamefield);

        this.setImage(Parameters.bossShipImage);
        this.setDirection(Direction.DOWN);
        this.getNode().setScaleX(VariousMagicNumbers.BOSS_SCALE);
        this.getNode().setScaleY(VariousMagicNumbers.BOSS_SCALE);
        this.setSpeed(VariousMagicNumbers.ZERO);
        this.getNode().setPickOnBounds(true);
    }
}
