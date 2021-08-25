package controller.enemyAI;

import Utilities.Direction;
import Utilities.Utilities;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import model.ship.BossShip;
import model.ship.SpaceShip;
import view.gameField.GameField;

public class BossAI {

  private final GameField gamefield;
  private SpaceShip bossShip;

  int prova = 0;

  private TreeMap<String, ScheduledFuture<?>> bossControls;
  private Runnable bossMovement;
  private Runnable bossFiring;

  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  public BossAI(GameField gamefield) {
    this.gamefield = gamefield;
    this.bossControls = new TreeMap<>();
    this.bossMovement = (() -> bossShip.invertDirection());
    this.bossFiring = (() -> bossShip.attack());
  }

  public void generateBoss() {
    bossShip = new BossShip(this.gamefield);
    bossShip.setPosition(20, -300);
    bossShip.setDirection(Direction.LEFT);
    bossShip.setSpeed(4);
    this.gamefield.addEnemyShip(bossShip);
    this.addBossTask("Movement");
    this.addBossTask("Firing");
  }

  public void updateBoss() {
    for (String e : bossControls.keySet()) {
      if (this.bossControls.get(e).isDone()) {
        this.addBossTask(e);
      }
    }
  }

  private void addBossTask(String taskName) {

    System.out.println("boss updated: " + prova);
    prova++;

    switch (taskName) {
      case "Movement":
        bossControls.put(
            "Movement",
            scheduler.schedule(
                bossMovement, Utilities.getRandomMillis(1.0, 4.0), TimeUnit.MILLISECONDS));
        break;
      case "Firing":
        bossControls.put(
            "Firing",
            scheduler.schedule(
                bossFiring, Utilities.getRandomMillis(0.0, 3.0), TimeUnit.MILLISECONDS));
        break;
      default:
    }
  }

  public void removeBoss() {
    this.gamefield.getGameContainer().getChildren().remove(this.bossShip.getNode());
    this.gamefield.getActiveEnemyShips().remove(this.bossShip);
    this.bossControls.clear();
  }
}
