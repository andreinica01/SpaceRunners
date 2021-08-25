package controller.enemyAI;

import Utilities.Utilities;
import controller.gameEventController.GameEventController;
import java.util.Iterator;
import java.util.Random;
import model.ship.EnemyShip;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import view.gameField.GameField;

/**
 * Class to manage the movement of the enemy ships
 */
public class enemyAI {

  private final GameField gamefield;
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

  public enemyAI(GameField gamefield, GameEventController gameEventController) {
    this.gamefield = gamefield;
    this.gameEvent = gameEventController;
    this.statusFactory = new StatusFactory();
    this.bossAI = new BossAI(this.gamefield);
    enemyResetTime = System.currentTimeMillis();
    statusResetTime = System.currentTimeMillis();
    enemyInterval = (long) (Utilities.getRandomDouble(0.0, 5.0) * 1000);
    statusInterval = (long) (Utilities.getRandomDouble(5.0, 15.0) * 1000);
    rnd = new Random();
  }

  public void update() {
    generateEnemy(checkAndSetDifficulty());
    generateStatus();
    manageBoss();
  }

  public void removeUnused() {
    Iterator<SpaceShip> ships = this.gamefield.getActiveEnemyShips().iterator();

    while (ships.hasNext()) {
      SpaceShip ship = ships.next();
      if (!this.gamefield.getGameContainer().getBoundsInLocal().contains(ship.getNode().getBoundsInParent())) {
        this.gamefield.getGameContainer().getChildren().remove(ship.getNode());
        ships.remove();
      }
    }
  }

  public void manageBoss() {
    if (this.playerPoints == 2 && !bossEnabled) {
      bossAI.generateBoss();
      this.bossEnabled = true;
    }
    if (bossEnabled) {
      bossAI.updateBoss();
    }
  }

  private void generateEnemy(Double difficultyFactor) {
    if (System.currentTimeMillis() - enemyResetTime > enemyInterval) {
      SpaceShip enemyship = new EnemyShip(this.gamefield);
      enemyship.setSpeed(enemyship.getSpeed().doubleValue() * difficultyFactor);
      enemyship.setPosition(-rnd.nextInt(400), -300);

      this.gamefield.addEnemyShip(enemyship);

      enemyInterval = (long) ((Utilities.getRandomDouble(0.0, 5 * 1 / difficultyFactor) * 1000));
      enemyResetTime = System.currentTimeMillis();

      removeUnused();
    }
  }

  private void generateStatus() {
    if (System.currentTimeMillis() - statusResetTime > statusInterval) {
      Status status = statusFactory.createStatus(StatusEnum.getRandom());
      status.setPosition(rnd.nextInt(400), -300);

      this.gamefield.addBonus(status);

      statusInterval = (long) ((Utilities.getRandomDouble(4.0, 15.0) * 1000));
      statusResetTime = System.currentTimeMillis();
    }
  }

  private Double checkAndSetDifficulty() {
    this.playerPoints = this.gameEvent.checkPoints();
    // Every 20 points difficulty increasing by 20%
    return 1 + (((double) this.playerPoints / 20) * 0.2);
  }
}
