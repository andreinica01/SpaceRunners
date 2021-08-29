package controller.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import Utilities.HUDParameters;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;

/**
 * Controller Status class. When certain condition are triggered, this class
 * apply the StatusEffect to the player.
 */
public class StatusController {

    private ScheduledExecutorService ses;
    private SpaceShip player;
    private HashMap<StatusEnum, Optional<ScheduledFuture<?>>> playerStatus;
    private Map<StatusEnum, Boolean> activeStatus;
    private Map<StatusEnum, Long> statusCooldown;
    private List<StatusEnum> statusEnumValues;

    /**
     * Create and Setting this StatusController to a SpaceShip instance.
     *
     * @param player
     */
    public StatusController(final SpaceShip player) {
        this.ses = Executors.newScheduledThreadPool(1);
        this.player = player;
        this.playerStatus = new HashMap<>();
        this.activeStatus = new HashMap<>();
        this.statusCooldown = new HashMap<>();
        this.statusEnumValues = List.of(StatusEnum.values());
        this.setPlayerStatus();
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
            this.addTemporaryEffect(status);
            return HUDParameters.TRUE;
        }
        // Else, refresh task's time
        task.get().cancel(HUDParameters.FALSE);
        this.addTemporaryEffect(status);
        return HUDParameters.TRUE;
    }

    private void addTemporaryEffect(final Status status) {
        // Applying effect
        this.ses.execute(status.getEffect());
        // Scheduling effect timeout, and add it to the local map
        var task = ses.schedule(status.getRemoveEffect(), status.getCoolDown(), TimeUnit.SECONDS);
        this.addTask(status, task);
    }

    private void addTask(final Status status, final ScheduledFuture<?> task) {
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
    
    /**
     * Mapping every Status with a boolean representing his own active state.
     * Example : <BonusSpeed, false> //BonusSpeed is not active <BonusSpeed, true>
     * //BonusSpeed is active
     * 
     * @return Map <StatusEnum, Boolean>
     */
    public Map<StatusEnum, Boolean> getActiveStatus() {
    	var map = this.getAllCooldown(TimeUnit.MILLISECONDS);
        Stream.of(StatusEnum.values())
        	  .forEach(e -> this.activeStatus.put(e, map.get(e) > 0));
        return this.activeStatus;
    }
    
    
    /**
     * Mapping every Status with his own (active) cooldown. Time is expressed by the
     * TimeUnit passed by argument. Example : <BonusSpeed, 5211> 
     * //BonusSpeed end in 5211 TimeUnit.
     * 
     * @param timeUnit
     * @return Map <StatusEnum, Long>
     */
    public Map<StatusEnum, Long> getAllCooldown(final TimeUnit timeUnit) {
    	
    	//Updating active status
        Stream.of(StatusEnum.values())
        .filter(e -> this.playerStatus.get(e).isPresent())
        .forEach(e -> this.statusCooldown.put(e, this.playerStatus.get(e).get().getDelay(timeUnit)));
        //Updating inactive status
        Stream.of(StatusEnum.values())
		.filter(e -> !this.playerStatus.get(e).isPresent())
        .forEach(e -> this.statusCooldown.put(e, Long.valueOf(HUDParameters.ZERO)));
        
        return this.statusCooldown;
    }
}