package model.menu;

import java.io.IOException;

public interface IEndGameGUI {

    /**
     * Ends the game and call a small GUI showing the results.
     *
     * @param points.
     * @throws IOException 
     * @throws Exception.
     */
    void end(int points) throws IOException;
}
