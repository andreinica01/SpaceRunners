package model.status.bonus;

import Utilities.HUDParameters;
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
        
        this.getNode().setRotate(HUDParameters.ZERO);
        this.setStatusName(StatusEnum.BonusSpeed);
        this.setCoolDown(HUDParameters.SEVEN); // 7 s
        this.setBoostFactor(HUDParameters.BOOST);

        this.setEffect(() -> this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * this.getBoostFactor()));

        this.setRemoveEffect(() -> {
            this.getPlayer().setSpeed(getPlayer().getSpeed().doubleValue() * (double) HUDParameters.ONE / this.getBoostFactor());
            

            /*
             * After the end of the effect we need to udpate collisions.
             */
            PhysicsEngineImpl.resetBound();
        });
    }
}