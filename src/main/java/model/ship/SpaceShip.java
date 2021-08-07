package model.ship;

import Utilities.Parameters;
import model.EntityImpl;

public class SpaceShip extends EntityImpl {

    private int lifePoints;
    private boolean invertedCommand;

    public SpaceShip() {
        this.lifePoints = Parameters.INITIAL_PLAYER_POINTS;
    }
    
    	


	public int getLifePoints() {
        return this.lifePoints;
    }
	
    
    public void setLifePoints (int lifepoint) {
    	this.lifePoints = lifepoint;
    }

    void increaseLifePoints(Number points) {
        this.lifePoints +=points.intValue();
    }

    void decreaselifePoints(Number points) {
        this.lifePoints -= points.intValue();
    }

	public boolean isInvertedCommand() {
		return invertedCommand;
	}

	public void setInvertedCommand(boolean invertedCommand) {
		this.invertedCommand = invertedCommand;
	}

}
