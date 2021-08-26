package model.status;

import Utilities.Direction;
import Utilities.Parameters;
import model.EntityImpl;
import model.ship.SpaceShip;

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
     * Constructor
     */
    public Status() {
        this.setDirection(Direction.DOWN);
        this.setSpeed(Parameters.STATUS_SPEED);
        this.setImage(Parameters.randomStatusImage);
    }

    public long getCoolDown() {
        return this.coolDown;
    }

    protected void setCoolDown(final long coolDown) {
        this.coolDown = coolDown;
    }

    public Runnable getEffect() {
        return this.effect;
    }

    protected void setEffect(final Runnable effect) {
        this.effect = effect;
    }

    public Runnable getRemoveEffect() {
        return this.removeEffect;
    }

    protected void setRemoveEffect(final Runnable removeEffect) {
        this.removeEffect = removeEffect;
    }

    public SpaceShip getPlayer() {
        return this.player;
    }

    public void setPlayer(final SpaceShip player) {
        this.player = player;
    }

    public StatusEnum getStatusName() {
        return this.statusName;
    }

    protected void setStatusName(final StatusEnum statusName) {
        this.statusName = statusName;
    }

    protected void setBoostFactor(double value) {
        this.boostFactor = value;
    }

    public double getBoostFactor() {
        return this.boostFactor;
    }
}