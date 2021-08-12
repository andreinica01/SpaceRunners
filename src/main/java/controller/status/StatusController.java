package controller.status;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
/**
 * Controller Status class.
 * When certain condition are triggered, this class apply the StatusEffect to the player.
 */
public class StatusController {

	private ScheduledExecutorService ses;
	private SpaceShip player;
	private HashMap<StatusEnum, Optional<ScheduledFuture<?>>> playerStatus;

	/**
	 * Create and Setting this StatusController to a SpaceShip instance.
	 * @param player
	 */
	public StatusController(SpaceShip player) {
		this.ses = Executors.newScheduledThreadPool(1);
		this.player = player;
		this.playerStatus = new HashMap<>();
		setPlayerStatus();
	}

	/**
	 * Applying StatusEffect to the Player.
	 * Every Status has his own expiring time.
	 * 
	 * @param Status status
	 */
	public void applyEffect(Status status) {

		Optional<ScheduledFuture<?>> task = playerStatus.get(status.getStatusName());
		//Adding effect if never added before or already terminated
		if (task.isEmpty() || task.get().isDone()) {
			addTemporaryEffect(status);
			return;
		}
		//Else, refresh task's time
		task.get().cancel(false);
		addTemporaryEffect(status);

	}

	private void addTemporaryEffect(Status status) {
		//Applying effect
		ses.execute(status.getEffect());
		//Scheduling effect timeout, and add it to the map
		var task = ses.schedule(status.getRemoveEffect(), status.getDuration(), TimeUnit.SECONDS);
		addTask(status, task);
	}
	
	private void addTask(Status status, ScheduledFuture<?> task) {
		this.playerStatus.put(status.getStatusName(), Optional.of(task));
	}

	private void setPlayerStatus() {
		for (StatusEnum s : StatusEnum.values()) {
			playerStatus.put(s, Optional.empty());
		}
	}

	public SpaceShip getPlayer() {
		return player;
	}
}
