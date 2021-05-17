package model.ship;

import Utilities.Parameters;
import model.EntityImpl;

public class SpaceShip extends EntityImpl {

    private int lifePoints;

    public SpaceShip() {
        this.lifePoints = Parameters.INITIAL_PLAYER_POINTS;

    }

    int getlifePoints() {
        return this.lifePoints;

    }

    void increaseLifePoints(Number points) {
        this.lifePoints +=points.intValue();
    }

    void decreaselifePoints(Number points) {
        this.lifePoints -= points.intValue();
    }

}
