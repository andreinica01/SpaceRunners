package model.status.bonus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

/** A Status that, below a certain maximum, add 1 bonus life to the player. */
public class BonusLife extends Status {

  private int MAXIMUM_LIFE = 4;

  public BonusLife() 
    super();
    setImage(Parameters.bonusLifeImage);
    setStatusName(StatusEnum.BonusLife);
    setCoolDown(0);

    setEffect(
        () -> {
          if (getPlayer().getLifePoints() < MAXIMUM_LIFE) getPlayer().increaseLifePoints(1);
        });

    setRemoveEffect(() -> {});
  }
}
