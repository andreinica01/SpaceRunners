package model.status.malus;

import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

/**
 * A Status that block player's firing.
 */
public class MalusFire extends Status {

  public MalusFire() {
    super();
    setImage(Parameters.malusFireImage);
    getNode().setRotate(0);
    setStatusName(StatusEnum.MalusFire);
    setCoolDown(7); //7 s

    setEffect(() -> getPlayer().setCanFire(false));
    setRemoveEffect(() -> getPlayer().setCanFire(true));
  }
}
