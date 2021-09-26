package model.menu;

import java.io.IOException;
import controller.gameSwitcher.SceneManager;

public class EndGameGUI implements IEndGameGUI {

    private SceneManager sceneManager;
    private int points;

    /*
     * Constructor.
     */
    public EndGameGUI(final SceneManager sceneManager, final int points) throws IOException {
    	this.sceneManager = sceneManager;
    	this.end(points);
    }

    @Override
    public final void end(final int points) throws IOException {
        this.points = points;
        this.sceneManager.switchToEndMenu();
    }
}
