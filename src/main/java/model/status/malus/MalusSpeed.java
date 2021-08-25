package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

/**
 * A Status that decrease player's speed of a certain amount.
 */
public class MalusSpeed extends Status {

  public MalusSpeed() {
    super();
    setImage(Parameters.malusSpeedImage);
    getNode().setRotate(0);
    setBoostFactor((double) 2 / 3);
    setStatusName(StatusEnum.MalusSpeed);
    setCoolDown(7); // 7 s

    setEffect(() -> getPlayer().setSpeed(getPlayer().getSpeed().doubleValue() * getBoostFactor()));

    setRemoveEffect(
      () ->
        getPlayer().setSpeed(getPlayer().getSpeed().doubleValue() * (double) 1 / getBoostFactor())
    );
  }
}
