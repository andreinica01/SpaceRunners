package model.hud;

public interface IEndGameGUI {

    /**
     * Ends the game and call a small GUI showing the results.
     *
     * @param points.
     * @throws Exception.
     */
    void end(int points) throws Exception;

    /**
     * After clicking the relative button, it will grant you to save and quit.
     */
    void saveAndQuit();

    /**
     * After clicking the relative button, it will grant you just quit.
     */
    void quit();
}
