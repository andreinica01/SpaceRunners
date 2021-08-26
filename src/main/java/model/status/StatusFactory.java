package model.status;

import model.status.bonus.BonusLife;
import model.status.bonus.BonusSpeed;
import model.status.malus.MalusCommand;
import model.status.malus.MalusFire;
import model.status.malus.MalusSpeed;

/** 
 * Factory in order to create Status Class. 
 */
public class StatusFactory {

    /**
     * Constructor
     */
    public StatusFactory() {
    }

    /**
     * @param type.
     * @return a specific status to be created.
     */
    public Status createStatus(final StatusEnum type) {
        switch (type) {
        case BonusLife:
            return new BonusLife();
        case BonusSpeed:
            return new BonusSpeed();
        case MalusCommand:
            return new MalusCommand();
        case MalusFire:
            return new MalusFire();
        case MalusSpeed:
            return new MalusSpeed();
        default:
            return null;
        }
    }
}