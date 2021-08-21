package model.ship;

import Utilities.Parameters;
import model.EntityImpl;

public class SpaceShip extends EntityImpl {

    private int lifePoints;
    private boolean invertedCommand;
    
    
    public SpaceShip() {
        this.lifePoints = Parameters.INITIAL_PLAYER_POINTS;
        setSpeed((double)10);
        setInvertedCommand(false);
        setCanFire(true);
    }

    
	public int getLifePoints() {
        return this.lifePoints;
    }
    
    public void setLifePoints (int lifepoint) {
    	this.lifePoints = lifepoint;
    }

    public void increaseLifePoints(Number points) {
        this.lifePoints +=points.intValue();
    }

    public void decreaselifePoints(Number points) {
        this.lifePoints -= points.intValue();
    }

	public boolean isInvertedCommand() {
		return invertedCommand;
	}

	public void setInvertedCommand(boolean invertedCommand) {
		this.invertedCommand = invertedCommand;
	}
	
	public boolean equals(SpaceShip p2) {
		if(this.lifePoints != p2.lifePoints)
			return false;
		if(this.invertedCommand != p2.invertedCommand)
			return false;
		if((double)this.getSpeed() != (double)p2.getSpeed())
			return false;
		if(this.getCanFire() != p2.getCanFire())
			return false;
		return true;
	}
	
	public SpaceShip clone() {
		SpaceShip newShip =  new SpaceShip();
		
		newShip.setLifePoints(this.lifePoints);
		newShip.setInvertedCommand(this.invertedCommand);
		newShip.setSpeed(this.getSpeed());
		newShip.setCanFire(this.getCanFire());
		return newShip;
	}

}
