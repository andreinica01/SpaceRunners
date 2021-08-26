package controller.status;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;

/**
 * Controller Status class. When certain condition are triggered, this class apply the StatusEffect
 * to the player.
 */
public class StatusController {

    private ScheduledExecutorService ses;
    private SpaceShip player;
    private HashMap<StatusEnum, Optional<ScheduledFuture<?>>> playerStatus;

    /**
     * Create and Setting this StatusController to a SpaceShip instance.
     *
     * @param player
     */
    public StatusController(final SpaceShip player) {
        this.ses = Executors.newScheduledThreadPool(1);
        this.player = player;
        this.playerStatus = new HashMap<>();
        setPlayerStatus();
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
            addTemporaryEffect(status);
            return true;
        }
        // Else, refresh task's time
        task.get().cancel(false);
        addTemporaryEffect(status);
        return false;
    }

    private void addTemporaryEffect(final Status status) {
        // Applying effect
        this.ses.execute(status.getEffect());
        // Scheduling effect timeout, and add it to the local map
        var task = ses.schedule(status.getRemoveEffect(), status.getCoolDown(), TimeUnit.SECONDS);
        addTask(status, task);
    }

    private void addTask(Status status, ScheduledFuture<?> task) {
        this.playerStatus.put(status.getStatusName(), Optional.of(task));
    }

    private void setPlayerStatus() {
        Stream.of(StatusEnum.values()).forEach(e -> this.playerStatus.put(e, Optional.empty()));
    }

    public HashMap<StatusEnum, Optional<ScheduledFuture<?>>> getPlayerStatus() {
        return this.playerStatus;
    }

    public SpaceShip getPlayer() {
        return this.player;
    }

    public HashMap<StatusEnum, Boolean> getActiveStatus() {
        HashMap<StatusEnum, Boolean> activeStatus = new HashMap<>();
        Stream.of(StatusEnum.values()).forEach(e -> activeStatus.put(e, false));
        Stream
            .of(StatusEnum.values())
            .filter(e -> Optional.ofNullable(this.playerStatus.get(e)).isPresent())
            .filter(e -> !this.playerStatus.get(e).get().isDone())
            .forEach(e -> activeStatus.put(e, true));
        return activeStatus;
    }
}
