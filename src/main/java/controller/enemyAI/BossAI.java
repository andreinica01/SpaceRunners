package controller.enemyAI;

import Utilities.Direction;
import Utilities.HUDParameters;
import Utilities.Utilities;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import model.ship.BossShip;
import view.gameField.GameField;

public class BossAI {

    private static final int X_POS = 20;
    private static final int Y_POS = -300;
    private final GameField gamefield;
    private BossShip bossShip;

    private Map<String, ScheduledFuture<?>> bossControls;
    private Runnable bossMovement;
    private Runnable bossFiring;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Constructor.
     * @param gamefield.
     */
    public BossAI(final GameField gamefield) {
        this.gamefield = gamefield;
        this.bossControls = new TreeMap<>();
        this.bossMovement = (() -> this.bossShip.invertDirection());
        this.bossFiring = (() -> this.bossShip.attack());
    }

    /**
     * Generate the boss.
     */
    public void generateBoss() {
        this.bossShip = new BossShip(this.gamefield);
        this.bossShip.setPosition(X_POS, Y_POS);
        this.bossShip.setDirection(Direction.LEFT);
        this.bossShip.setSpeed(HUDParameters.FOUR);
        this.gamefield.addBoss(bossShip);
        this.addBossTask("Movement");
        this.addBossTask("Firing");
    }

    /**
     * Update boss entity.
     */
    public void updateBoss() {
        this.bossControls.keySet().stream().filter(e -> this.bossControls.get(e).isDone()).forEach(e -> this.addBossTask(e));
    }

    /**
     * Add boss behaviour.
     * @param taskName
     */
    private void addBossTask(final String taskName) {
        
        switch (taskName) {
        case "Movement":
            this.bossControls.put("Movement",
                    this.scheduler.schedule(bossMovement, Utilities.getRandomMillis(1.0, 4.0), TimeUnit.MILLISECONDS));
            break;
        case "Firing":
            this.bossControls.put("Firing",
                    this.scheduler.schedule(bossFiring, Utilities.getRandomMillis(0.0, 3.0), TimeUnit.MILLISECONDS));
            break;
        default:
        }
    }

    /**
     * Remove the boss from the game.
     */
    public void removeBoss() {
        this.gamefield.getGameContainer().getChildren().remove(this.bossShip.getNode());
        this.gamefield.getActiveEnemyShips().remove(this.bossShip);
        this.bossControls.clear();
    }
}