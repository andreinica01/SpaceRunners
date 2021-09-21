package controller.collisionEngine;

import model.hud.HUDBonusImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import utilities.VariousMagicNumbers;

public abstract class AbstractHelper implements PhysicsEngine {

     /*
     * Control fields.
     */
    private HUDPointsImpl pointsHUD;
    private HUDLifeImpl livesHUD;
    private HUDBonusImpl bonusHUD;
    private int bossHP;

    /**
     * Resets border due to changing movement speed.
     */
    protected static double resetX;

    /*
     * Constructor.
     */
    public AbstractHelper(final HUDPointsImpl pointsHUD,
    		final HUDLifeImpl livesHUD, final HUDBonusImpl bonusHUD) {
    	this.pointsHUD = pointsHUD;
    	this.livesHUD = livesHUD;
    	this.bonusHUD = bonusHUD;

    	this.bossHP = VariousMagicNumbers.SEVEN;
    }

	@Override
	public abstract void update();

    /*
     * HUD change methods
     */
    @Override
    public final void removePoints() {
        this.pointsHUD.pointsDown();
    }

    @Override
    public final void removeLife() {
        this.livesHUD.lifeDown();
    }

    @Override
    public final void addPoints() {
        this.pointsHUD.pointsUp();
    }

    @Override
    public final void addLife() {
        this.livesHUD.lifeUp();
    }

    /*
     * Getter
     */
	@Override
	public final HUDPointsImpl getPointsHUD() {
		return this.pointsHUD;
	}

	@Override
	public final HUDLifeImpl getLifeHUD() {
		return this.livesHUD;
	}

	@Override
	public final HUDBonusImpl getBonusHUD() {
		return this.bonusHUD;
	}

	@Override
    public final int getBossHP() {
        return this.bossHP;
    }

    @Override
    public final void setBossHP(final int value) {
    	this.bossHP = value;
    }

    /**
     * Static method used to reset the bounds collision every time
     * a bonus runs off. Static because I want this to be called
     * from the outside without referencing it as a bonus.
     */
    public static void resetBounds() {
        resetX = VariousMagicNumbers.FOUR;
    }
}
