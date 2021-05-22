package model.status;

import java.util.Timer;
import java.util.TimerTask;

public class StatusTimer extends TimerTask {

	private Runnable runnable;
	private Timer timer;

	public StatusTimer(Runnable run, Timer timer) {
		this.runnable = run;
		this.timer = timer;
	}

	@Override
	public void run() {
		this.runnable.run();
		this.timer.cancel();
	}

	public Runnable getRunnable() {
		return runnable;
	}

	public void setRunnable(Runnable run) {
		this.runnable = run;
	}

}
