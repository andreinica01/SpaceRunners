package model.status;

import model.EntityImpl;
import model.ship.SpaceShip;
import utilities.Direction;
import utilities.MagicEnumComplexImage;
import utilities.MagicEnumInt;

/**
 * An entity that can apply, under certain condition, an effect (bonus or malus)
 * to the player. Any type of effect are temporary, each of them have a
 * different cooldown.
 */
public abstract class Status extends EntityImpl {

	private long coolDown; // in s
	private double boostFactor;

	private SpaceShip player;
	private Runnable effect;
	private Runnable removeEffect;
	private StatusEnum statusName;

	/**
	 * Constructor.
	 */
	public Status() {
		this.setDirection(Direction.DOWN);
		this.setSpeed(MagicEnumInt.STATUS_SPEED.getValue());
		this.setImage(MagicEnumComplexImage.RANDOM_STATUS.getImage());
		this.getNode().setRotate(MagicEnumInt.ZERO.getValue());
	}

	/**
	 * @return status cooldown.
	 */
	public long getCoolDown() {
		return this.coolDown;
	}

	/**
	 * Sets status cooldown.
	 * 
	 * @param coolDown
	 */
	protected void setCoolDown(final long coolDown) {
		this.coolDown = coolDown;
	}

	/**
	 * @return status effect.
	 */
	public Runnable getEffect() {
		return this.effect;
	}

	/**
	 * Sets status effect.
	 * 
	 * @param effect
	 */
	protected void setEffect(final Runnable effect) {
		this.effect = effect;
	}

	/**
	 * @return effect to be removed.
	 */
	public Runnable getRemoveEffect() {
		return this.removeEffect;
	}

	/**
	 * Sets the effect to be removed.
	 * 
	 * @param removeEffect
	 */
	protected void setRemoveEffect(final Runnable removeEffect) {
		this.removeEffect = removeEffect;
	}

	/**
	 * @return player.
	 */
	public SpaceShip getPlayer() {
		return this.player;
	}

	/**
	 * Sets Player reference.
	 * 
	 * @param player
	 */
	public void setPlayer(final SpaceShip player) {
		this.player = player;
	}

	/**
	 * @return status name.
	 */
	public StatusEnum getStatusName() {
		return this.statusName;
	}

	/**
	 * Sets a status name.
	 * 
	 * @param statusName
	 */
	protected void setStatusName(final StatusEnum statusName) {
		this.statusName = statusName;
	}

	/**
	 * Sets the boost factor.
	 * 
	 * @param value
	 */
	protected void setBoostFactor(final double value) {
		this.boostFactor = value;
	}

	/**
	 * @return boost factor.
	 */
	public double getBoostFactor() {
		return this.boostFactor;
	}
}
