package model.status.malus;

import controller.collisionEngine.AbstractHelper;
import model.status.Status;
import model.status.StatusEnum;
import utilities.MagicEnumDouble;
import utilities.MagicEnumInt;

/**
 * A Status that decrease player's speed of a certain amount.
 */
public class MalusSpeed extends Status {

    /**
     * Constructor.
     */
    public MalusSpeed() {
        super();

        this.setBoostFactor(MagicEnumDouble.SLOW.getValue());
        this.setStatusName(StatusEnum.MalusSpeed);
        this.setCoolDown(MagicEnumInt.SEVEN.getValue()); // 7 s

        this.setEffect(() -> 
            this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * this.getBoostFactor()));

        this.setRemoveEffect(() -> {
            this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * (double) MagicEnumInt.ONE.getValue() / this.getBoostFactor());

            /*
             * After the end of the effect we need to udpate collisions.
             */
            AbstractHelper.resetBounds();
        });
    }
}
