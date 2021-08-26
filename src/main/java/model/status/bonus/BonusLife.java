package model.status.bonus;

import Utilities.HUDParameters;
import Utilities.Parameters;
import model.status.Status;
import model.status.StatusEnum;

/** 
 * A Status that, below a certain maximum, add 1 bonus life to the player. 
 */
public class BonusLife extends Status {

    /**
     * Constructor
     */
    public BonusLife() {
        super();
        
        this.setImage(Parameters.bonusLifeImage);
        this.setStatusName(StatusEnum.BonusLife);
        this.setCoolDown(0);

<<<<<<< HEAD
  public BonusLife() 
    super();
    setImage(Parameters.bonusLifeImage);
    setStatusName(StatusEnum.BonusLife);
    setCoolDown(0);

    setEffect(
        () -> {
          if (getPlayer().getLifePoints() < MAXIMUM_LIFE) getPlayer().increaseLifePoints(1);
=======
        this.setEffect(() -> {
            if (this.getPlayer().getLifePoints() < HUDParameters.MAX_LIFE_POINTS)
                this.getPlayer().increaseLifePoints(1);
>>>>>>> c0682ea3308a7c60a92bdb2510a28e2e1fcc688c
        });

        this.setRemoveEffect(() -> {
        });
    }
}