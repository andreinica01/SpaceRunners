package model.status.bonus;

import controller.collisionEngine.PhysicsEngineImpl;
import model.status.Status;
import model.status.StatusEnum;
import utilities.HUDParameters;
import utilities.VariousMagicNumbers;

/** 
 * A Status that boost player's speed of a certain amount. 
 */
public class BonusSpeed extends Status {

    /**
     * Constructor.
     */
    public BonusSpeed() {
        super();

        this.setStatusName(StatusEnum.BonusSpeed);
        this.setCoolDown(VariousMagicNumbers.SEVEN); // 7 s
        this.setBoostFactor(HUDParameters.BOOST);

        this.setEffect(() -> this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * this.getBoostFactor()));

        this.setRemoveEffect(() -> {
            this.getPlayer().setSpeed(getPlayer().getSpeed().doubleValue() * (double) VariousMagicNumbers.ONE / this.getBoostFactor());


            /*
             * After the end of the effect we need to udpate collisions.
             */
            PhysicsEngineImpl.resetBound();
        });
    }
}
