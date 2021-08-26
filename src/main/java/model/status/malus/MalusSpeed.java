package model.status.malus;

import Utilities.Parameters;
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
        
        this.setImage(Parameters.malusSpeedImage);
        this.getNode().setRotate(0);
        this.setBoostFactor((double) 2 / 3);
        this.setStatusName(StatusEnum.MalusSpeed);
        this.setCoolDown(7); // 7 s

        this.setEffect(() -> 
            this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * this.getBoostFactor()));

        this.setRemoveEffect(() -> {
            this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * (double) 1 / this.getBoostFactor());
            
            /*
             * After the end of the effect we need to udpate collisions.
             */
            PhysicsEngineImpl.resetBound();
        });
    }
}