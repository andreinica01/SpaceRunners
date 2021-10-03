package controller.enemyAI;

import controller.gameEventController.GameEventController;
import java.util.Iterator;
import java.util.Random;
import model.ship.EnemyNounShip;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import utilities.Utilities;
import utilities.VariousMagicNumbers;
import view.gameField.GameField;

/** 
 * Class to manage the movement of the enemy ships.
 */
public class EnemyAI {

	private static final int MULTIPLIER = 1000;
	private static final int ENEMY_Y = -180;
	private static final int RAND_X = 400;
	private static final int STATUS_Y = -300;
	private static final double FIRST_VALUE = 5.0;
	private static final double SECOND_VALUE = 5.0;
	private static final double THIRD_VALUE = 15.0;
	private static final double DIFFICULTY_MULTIPLIER = 0.2;
	private static final double ZERO = 0.0;
	private static final double FOUR = 4.0;
	
    private final GameField gameField;
    private final GameEventController gameEvent;
    private final StatusFactory statusFactory;

    private int playerPoints;
    private int bossSpawned;
    private boolean bossEnabled;

    private long enemyInterval;
    private long enemyResetTime;
    private long statusInterval;
    private long statusResetTime;

    private final Random rnd;
    private final BossAI bossAI;

    /**
     * Constructor.
     * @param gamefield
     * @param gameEventController
     */
    public EnemyAI(final GameField gamefield, final GameEventController gameEventController) {
        this.gameField = gamefield;
        this.gameEvent = gameEventController;
        this.statusFactory = new StatusFactory();
        this.bossAI = new BossAI(this.gameField);
        this.bossSpawned = VariousMagicNumbers.ONE;
        this.enemyResetTime = System.currentTimeMillis();
        this.statusResetTime = System.currentTimeMillis();
        this.enemyInterval = (long) (Utilities.getRandomDouble(FIRST_VALUE, SECOND_VALUE) * MULTIPLIER);
        this.statusInterval = (long) (Utilities.getRandomDouble(SECOND_VALUE, THIRD_VALUE) * MULTIPLIER);
        this.rnd = new Random();
    }

    /**
     * Game update.
     */
    public void update() {
        this.generateEnemy(this.checkAndSetDifficulty());
        this.generateStatus();
        this.manageBoss();
        this.checkBossDeath();
    }

	/**
     * Remove undestroyed ships.
     */
    public void removeUnused() {
        Iterator<SpaceShip> ships = this.gameField.getActiveEnemyShips().iterator();

        while (ships.hasNext()) {
            SpaceShip ship = ships.next();
            if (!this.gameField.getGameContainer().getBoundsInLocal().contains(ship.getNode().getBoundsInParent())) {
                this.gameField.getGameContainer().getChildren().remove(ship.getNode());
                ships.remove();
            }
        }
    }

    /**
     * Check if a boss is dead and in case change a value for next spawn.
     */
    private void checkBossDeath() {
		if (this.gameField.isBossToBeSpawned()) {
			this.bossEnabled = VariousMagicNumbers.FALSE;
		}
	}

    /**
     * This method handle boos spawn system.
     */
    public void manageBoss() {
        if (this.playerPoints == VariousMagicNumbers.TEN * this.bossSpawned && !this.bossEnabled) {
            this.bossAI.generateBoss();
        	this.bossSpawned++;
            this.bossEnabled = VariousMagicNumbers.TRUE;
        }
        if (this.bossEnabled) {
            this.bossAI.updateBoss();
        }
    }

    /**
     * Generate enemy entities based on current difficulty.
     * @param difficultyFactor
     */
    private void generateEnemy(final Double difficultyFactor) {
        if (System.currentTimeMillis() - this.enemyResetTime > this.enemyInterval) {
            SpaceShip enemyship = new EnemyNounShip(this.gameField);
            enemyship.setSpeed(enemyship.getSpeed().doubleValue() * difficultyFactor);
            enemyship.setPosition(this.rnd.nextInt(RAND_X), ENEMY_Y);

            this.gameField.addEnemyShip(enemyship);
            this.gameField.getSoundManager().playShipPassing();

            this.enemyInterval = (long) 
            		((Utilities.getRandomDouble(
            				ZERO, VariousMagicNumbers.FIVE * VariousMagicNumbers.ONE
            				/ difficultyFactor) * MULTIPLIER));
            this.enemyResetTime = System.currentTimeMillis();

            this.removeUnused();
        }
    }

    /**
     * Generate status modifier.
     */
    private void generateStatus() {
        if (System.currentTimeMillis() - this.statusResetTime > this.statusInterval) {
            Status status = this.statusFactory.createStatus(StatusEnum.getRandom());
            status.setPosition(this.rnd.nextInt(RAND_X), STATUS_Y);

            this.gameField.addBonus(status);

            this.statusInterval = (long) 
            		((Utilities.getRandomDouble(FOUR, THIRD_VALUE) * MULTIPLIER));
            this.statusResetTime = System.currentTimeMillis();
        }
    }

    /**
     * @return current difficulty.
     */
    private Double checkAndSetDifficulty() {
        this.playerPoints = this.gameEvent.checkPoints();
        // Every 20 points difficulty increasing by 20%
        return VariousMagicNumbers.ONE 
        		+ (((double) this.playerPoints / VariousMagicNumbers.TWENTY)
        				* DIFFICULTY_MULTIPLIER);
    }
}
