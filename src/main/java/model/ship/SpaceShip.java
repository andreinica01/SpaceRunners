package model.ship;

import model.EntityImpl;
import utilities.MagicEnumInt;
import view.gameField.GameField;

public abstract class SpaceShip extends EntityImpl {

    private int lifePoints;
    private boolean invertedCommand;
    private GameField gameField;

    /**
     * Constructor.
     * @param gamefield
     */
    public SpaceShip(final GameField gamefield) {

        this.setSpeed(10);
        this.setInvertedCommand(false);
        this.setCanFire(true);

        this.gameField = gamefield;
    }

    /**
     * Return spaceship lifepoints.
     * @return
     */
    public int getLifePoints() {
        return this.lifePoints;
    }

    /**
     * Set space ship lifepoints.
     * @param lifepoint
     */
    public void setLifePoints(final int lifepoint) {
        this.lifePoints = lifepoint;
    }

    /**
     * Increase lifepoints.
     * @param points
     */
    public void increaseLifePoints(final Number points) {
        this.lifePoints += points.intValue();
    }

    /**
     * Decrease lifepoints.
     * @param points
     */
    public void decreaselifePoints(final Number points) {
        this.lifePoints -= points.intValue();
    }

    /**
     * @return true if commands are inverted
     */
    public boolean isInvertedCommand() {
        return this.invertedCommand;
    }

    /**
     * Set if the commands are inverted or not.
     * @param invertedCommand
     */
    public void setInvertedCommand(final boolean invertedCommand) {
        this.invertedCommand = invertedCommand;
    }

    /**
     * Cause the boss to move down of some frames.
     */
	public final void drop() {
		this.setPosition(this.getPosition().getX().intValue(), 
				this.getPosition().getY().intValue() + MagicEnumInt.TEN.getValue());
	}

	/**
	 * @return GameField reference.
	 */
	public GameField getGameFieldReference() {
		return this.gameField;
	}

    /** 
     * attack.
     */
    public abstract void attack();
}
