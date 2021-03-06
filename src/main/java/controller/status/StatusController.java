package controller.status;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import utilities.MagicEnumInt;

/**
 * Controller Status class. When certain condition are triggered, this class
 * apply the StatusEffect of an Status instance to the player.
 */
public class StatusController {

    private ScheduledExecutorService ses;
    private SpaceShip player;
    private Map<StatusEnum, Optional<ScheduledFuture<?>>> playerStatus;
    private Map<StatusEnum, Boolean> activeStatus;
    private Map<StatusEnum, Long> statusCooldown;

    /**
     * Create and link this StatusController to a SpaceShip instance.
     *
     * @param player
     */
    public StatusController(final SpaceShip player) {
        this.ses = Executors.newScheduledThreadPool(1);
        this.player = player;
        this.playerStatus = new HashMap<>();
        this.activeStatus = new HashMap<>();
        this.statusCooldown = new HashMap<>();
        this.initializePlayerStatus();
    }

    /**
     * Applying StatusEffect to the Player. Every Status has his own expiring time.
     *
     * @param Status status
     */
    public boolean applyEffect(final Status status) {
        status.setPlayer(this.player);
        Optional<ScheduledFuture<?>> task = this.playerStatus.get(status.getStatusName());
        // Adding effect if never added before or already terminated
        if (task.isEmpty() || task.get().isDone()) {
            this.ses.execute(status.getEffect());
            this.addDebuffTask(status);
            return true;
        }
        // Else, refresh task's time
        task.get().cancel(false);
        this.addDebuffTask(status);
        return true;
    }

    /**
     * Schedule remove effect task. This is needed in order to disable the previous
     * status effect.
     * 
     * @param status
     */
    private void addDebuffTask(final Status status) {
        // Scheduling effect timeout, and add it to the local map
        var task = ses.schedule(status.getRemoveEffect(), status.getCoolDown(), TimeUnit.SECONDS);
        this.addTask(status, task);
    }

    /**
     * Schedule a task. Adding a scheduled task.
     * 
     * @param status
     * @param task
     */
    private void addTask(final Status status, final ScheduledFuture<?> task) {
        this.playerStatus.put(status.getStatusName(), Optional.of(task));
    }

    /**
     * Initialize all the status of Player.
     */
    private void initializePlayerStatus() {
        Stream.of(StatusEnum.values()).forEach(e -> this.playerStatus.put(e, Optional.empty()));
    }

    /**
     * Get the Map mapping all the type of status of the player with his own
     * scheduled task.
     * 
     * @return Map mapping all the type of status of the player with his own
     *         scheduled task
     */
    public final Map<StatusEnum, Optional<ScheduledFuture<?>>> getPlayerStatus() {
        return this.playerStatus;
    }

    /**
     * Get the instance of this Player.
     * 
     * @return instance of this Player
     */
    public final SpaceShip getPlayer() {
        return this.player;
    }

    /**
     * Mapping every Status with a boolean representing his own active state.
     * Example : (BonusSpeed, false) -> BonusSpeed is not active. (BonusSpeed, true)
     * -> BonusSpeed is active.
     * 
     * @return Map <StatusEnum, >
     */
    public Map<StatusEnum, Boolean> getActiveStatus() {
        var map = this.getAllCooldown(TimeUnit.MILLISECONDS);
        Stream.of(StatusEnum.values()).forEach(e -> this.activeStatus.put(e, map.get(e) > 0));
        return this.activeStatus;
    }

    /**
     * Mapping every Status with his own (active) cooldown. Time is expressed by the
     * TimeUnit passed by argument. Example : (BonusSpeed, 5211) -> BonusSpeed end
     * in 5211 TimeUnit.
     * 
     * @param timeUnit
     * @return Map <StatusEnum, Long>
     */
    public Map<StatusEnum, Long> getAllCooldown(final TimeUnit timeUnit) {

        // Updating active status
        Stream.of(StatusEnum.values()).filter(e -> this.playerStatus.get(e).isPresent())
                .forEach(e -> this.statusCooldown.put(e, this.playerStatus.get(e).get().getDelay(timeUnit)));
        // Updating inactive status
        Stream.of(StatusEnum.values()).filter(e -> !this.playerStatus.get(e).isPresent())
                .forEach(e -> this.statusCooldown.put(e, Long.valueOf(MagicEnumInt.ZERO.getValue())));

        return this.statusCooldown;
    }
}
