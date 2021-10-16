package model.ship;

import utilities.Direction;
import utilities.MagicEnumDouble;
import utilities.MagicEnumImage;
import utilities.MagicEnumInt;
import view.gameField.GameField;

public class BossShip extends EnemyShip {

    /**
     * Constructor.
     * 
     * @param gamefield
     */
    public BossShip(final GameField gamefield) {
        super(gamefield);

        this.setImage(MagicEnumImage.BOSS.getImage());
        this.setDirection(Direction.DOWN);
        this.getNode().setScaleX(MagicEnumDouble.BOSS_SCALE.getValue());
        this.getNode().setScaleY(MagicEnumDouble.BOSS_SCALE.getValue());
        this.setSpeed(MagicEnumInt.ZERO.getValue());
        this.getNode().setPickOnBounds(true);
    }
}
