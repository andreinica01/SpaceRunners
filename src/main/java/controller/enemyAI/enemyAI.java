package controller.enemyAI;

import Utilities.Utilities;
import controller.gameEventController.GameEventController;
import java.util.Iterator;
import java.util.Random;
import model.ship.EnemyNounShip;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import view.gameField.GameField;

/** 
 * Class to manage the movement of the enemy ships 
 */
public class enemyAI {

    private final GameField gameField;
    private final GameEventController gameEvent;
    private final StatusFactory statusFactory;

    private int playerPoints;
    private boolean bossEnabled;

    long enemyInterval;
    long enemyResetTime;

    long statusInterval;
    long statusResetTime;

    private final Random rnd;
    private final BossAI bossAI;

    /**
     * Constructor.
     * @param gamefield
     * @param gameEventController
     */
    public enemyAI(final GameField gamefield, final GameEventController gameEventController) {
        this.gameField = gamefield;
        this.gameEvent = gameEventController;
        this.statusFactory = new StatusFactory();
        this.bossAI = new BossAI(this.gameField);
        this.enemyResetTime = System.currentTimeMillis();
        this.statusResetTime = System.currentTimeMillis();
        this.enemyInterval = (long) (Utilities.getRandomDouble(0.0, 5.0) * 1000);
        this.statusInterval = (long) (Utilities.getRandomDouble(5.0, 15.0) * 1000);
        this.rnd = new Random();
    }

    /**
     * Game update.
     */
    public void update() {
        this.generateEnemy(this.checkAndSetDifficulty());
        this.generateStatus();
        this.manageBoss();
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
     * This method handle boos spawn system.
     */
    public void manageBoss() {
        if (this.playerPoints == 2 && !this.bossEnabled) {
            this.bossAI.generateBoss();
            this.bossEnabled = true;
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
            enemyship.setPosition(this.rnd.nextInt(400), -180);

            this.gameField.addEnemyShip(enemyship);
            this.gameField.getSoundManager().playShipPassing();

            enemyInterval = (long) ((Utilities.getRandomDouble(0.0, 5 * 1 / difficultyFactor) * 1000));
            enemyResetTime = System.currentTimeMillis();

            this.removeUnused();
        }
    }

    /**
     * Generate status modifier.
     */
    private void generateStatus() {
        if (System.currentTimeMillis() - this.statusResetTime > this.statusInterval) {
            Status status = this.statusFactory.createStatus(StatusEnum.getRandom());
            status.setPosition(this.rnd.nextInt(400), -300);

            this.gameField.addBonus(status);

            statusInterval = (long) ((Utilities.getRandomDouble(4.0, 15.0) * 1000));
            statusResetTime = System.currentTimeMillis();
        }
    }

    /**
     * @return current difficulty.
     */
    private Double checkAndSetDifficulty() {
        this.playerPoints = this.gameEvent.checkPoints();
        // Every 20 points difficulty increasing by 20%
        return 1 + (((double) this.playerPoints / 20) * 0.2);
    }
}