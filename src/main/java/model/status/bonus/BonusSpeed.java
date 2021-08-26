package model.status.bonus;

import Utilities.Parameters;
import controller.collisionEngine.PhysicsEngineImpl;
import model.status.Status;
import model.status.StatusEnum;

/** A Status that boost player's speed of a certain amount. */
public class BonusSpeed extends Status {

  public BonusSpeed() {
    super();
    setImage(Parameters.bonusSpeedImage);
    getNode().setRotate(0);
    setStatusName(StatusEnum.BonusSpeed);
    setCoolDown(7); // 7 s
    setBoostFactor((double) 3 / 2);

    setEffect(() -> getPlayer().setSpeed(getPlayer().getSpeed().doubleValue() * getBoostFactor()));

    setRemoveEffect(
        () ->
        {
            getPlayer()
                .setSpeed(getPlayer().getSpeed().doubleValue() * (double) 1 / getBoostFactor());
            PhysicsEngineImpl.resetBound();
        });
  }
}
