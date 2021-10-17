package model.status;

import model.EntityImpl;
import model.ship.SpaceShip;
import utilities.Direction;
import utilities.MagicEnumComplexImage;
import utilities.MagicEnumInt;

/**
 * An entity that store an effect (bonus or malus), that can be applied to an
 * entity. Any type of effect are temporary, each of them have a different
 * cooldown.
 */
public abstract class Status extends EntityImpl {

    private long coolDown; // in seconds
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
     * Get the maximum cooldown in seconds of this status.
     * 
     * @return maximum cooldown in seconds of this status
     */
    public long getCoolDown() {
        return this.coolDown;
    }

    /**
     * Sets this maximum status cooldown in seconds.
     * 
     * @param cooldown maximum cooldown of this status in seconds
     */
    public void setCoolDown(final long coolDown) {
        this.coolDown = coolDown;
    }

    /**
     * Get the runnable representing the effect of this status.
     * 
     * @return this status effect.
     */
    public Runnable getEffect() {
        return this.effect;
    }

    /**
     * Sets the runnable representing the effect of this status.
     * 
     * @param effect effect of this status
     */
    public void setEffect(final Runnable effect) {
        this.effect = effect;
    }

    /**
     * Get the runnable representing the remove effect of this status.
     * 
     * @return the remove effect to this status
     */
    public Runnable getRemoveEffect() {
        return this.removeEffect;
    }

    /**
     * Set the runnable representing the remove effect of this status.
     * 
     * @param removeEffect remove effect of this status
     */
    public void setRemoveEffect(final Runnable removeEffect) {
        this.removeEffect = removeEffect;
    }

    /**
     * Get Player reference.
     * 
     * @return player
     */
    public SpaceShip getPlayer() {
        return this.player;
    }

    /**
     * Set Player reference.
     * 
     * @param player
     */
    public void setPlayer(final SpaceShip player) {
        this.player = player;
    }

    /**
     * Get the name of this status type.
     * 
     * @return status type name.
     */
    public StatusEnum getStatusName() {
        return this.statusName;
    }

    /**
     * Set the name of this status type.
     * 
     * @param statusName
     */
    public void setStatusName(final StatusEnum statusName) {
        this.statusName = statusName;
    }

    /**
     * Set the value of this boost factor.
     * 
     * @param value of this boost factor
     */
    public void setBoostFactor(final double value) {
        this.boostFactor = value;
    }

    /**
     * Get the value of this boost factor.
     * 
     * @return value of this boost factor.
     */
    public double getBoostFactor() {
        return this.boostFactor;
    }
}
