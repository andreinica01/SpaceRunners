package controller.gameLoop;

import controller.gameController.GameContollerImpl;
import controller.gameController.GameController;
import controller.gameSwitcher.SceneManager;
import javafx.animation.AnimationTimer;
import utilities.Parameters;
import utilities.VariousMagicNumbers;
import view.gameField.GameFieldImpl;
import view.mainWindow.MainWindow;
import view.mainWindow.MainWindowImpl;

public class GameManager extends AnimationTimer {

	private static final long SLEEP = 10000000;
	private static final int SLEEP_TIMER = 100000;

    long delta;
    long prevtime;

    private MainWindow mainwindow;
    private GameFieldImpl gamefield;
    private GameController gameController;
    private SceneManager sceneManager;
	private String setPlayerName;

    public GameManager(final SceneManager sceneManager) {
    	this.sceneManager = sceneManager;
        this.mainwindow = new MainWindowImpl(this.sceneManager);

        this.gamefield = new GameFieldImpl(Parameters.WIDTH, Parameters.HEIGHT, this);
        this.mainwindow.addGameField(this.gamefield);

        this.gameController = new GameContollerImpl(this.gamefield);

        this.mainwindow.setWidth(Parameters.WIDTH);
        this.mainwindow.setHeight(Parameters.HEIGHT);
        this.mainwindow.setVisible(VariousMagicNumbers.TRUE);
    }

    /**
     * @param delta value.
     * @return the actual frame rate base on value passed.
     */
    public double getFrameRateHertz(final long delta) {
        double frameRate = 1d / delta;
        return frameRate * 1e9;
    }

    @Override
    public final void handle(final long now) {
        /*
         * if ((now - prevtime) < sleepNs) { ("exit"); return; }
         */
        try {
            Thread.sleep(0, SLEEP_TIMER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.gameController.update();
        this.prevtime = now;
    }

    /** 
     * @return time sleep value 
     */
    public long getTimeSleep() {
        return SLEEP;
    }

    /**
     * @return the GameManager.
     */
    public GameManager getManager() {
        return this;
    }
    
    public SceneManager getSceneManager() {
    	return this.sceneManager;
    }

	public void setPlayerName(String name) {
		this.setPlayerName = name;
	}
	
	public String getPlayerName() {
		return this.setPlayerName;
	}
}
