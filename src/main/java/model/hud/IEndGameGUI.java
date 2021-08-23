package model.hud;

public interface IEndGameGUI {
    
    /**
     * Ends the game and call a small GUI showing the results
     * @param points
     * @param level
     * @throws Exception 
     */
    public void end(int points, int level) throws Exception;
    
    /**
     * After clicking the relative button, it will grant you to save and quit.
     */
    public void saveAndQuit();
    
    /**
     * After clicking the relative button, it will grant you just quit.
     */
    public void quit();

}
