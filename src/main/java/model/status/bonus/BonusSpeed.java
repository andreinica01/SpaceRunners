package model.status.bonus;

import Utilities.Parameters;
import controller.collisionEngine.PhysicsEngineImpl;
import model.status.Status;
import model.status.StatusEnum;

/** 
 * A Status that boost player's speed of a certain amount. 
 */
public class BonusSpeed extends Status {

    /**
     * Constructor
     */
    public BonusSpeed() {
        super();
        
        this.setImage(Parameters.bonusSpeedImage);
        this.getNode().setRotate(0);
        this.setStatusName(StatusEnum.BonusSpeed);
        this.setCoolDown(7); // 7 s
        this.setBoostFactor((double) 3 / 2);

        this.setEffect(() -> this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * this.getBoostFactor()));

        this.setRemoveEffect(() -> {
            this.getPlayer().setSpeed(getPlayer().getSpeed().doubleValue() * (double) 1 / getBoostFactor());

            /*
             * After the end of the effect we need to udpate collisions.
             */
            PhysicsEngineImpl.resetBound();
        });
    }
}