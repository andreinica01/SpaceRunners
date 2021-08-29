package model.status.malus;

import Utilities.HUDParameters;
import controller.collisionEngine.PhysicsEngineImpl;
import model.status.Status;
import model.status.StatusEnum;

/**
 * A Status that decrease player's speed of a certain amount.
 */
public class MalusSpeed extends Status {

    /**
     * Constructor
     */
    public MalusSpeed() {
        super();
        
        this.getNode().setRotate(HUDParameters.ZERO);
        this.setBoostFactor(HUDParameters.SLOW);
        this.setStatusName(StatusEnum.MalusSpeed);
        this.setCoolDown(HUDParameters.SEVEN); // 7 s

        this.setEffect(() -> 
            this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * this.getBoostFactor()));

        this.setRemoveEffect(() -> {
            this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * (double) HUDParameters.ONE / this.getBoostFactor());
            
            /*
             * After the end of the effect we need to udpate collisions.
             */
            PhysicsEngineImpl.resetBound();
        });
    }
}