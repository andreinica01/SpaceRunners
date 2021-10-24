package controller.ranking;

import java.io.IOException;

public interface Ranking {

    /**
     * Add a new player's score to the ranking system.
     * 
     * @param playerName
     * @param playerScore
     * @throws IOException
     */
    void addPlayer(String playerName, Integer playerScore) throws IOException;

    /**
     * Return a formatted String containing a limited part of the information of
     * this ranking map.
     * 
     * @return a formatted String
     */
    String getFormattedRankingMap(int limit);
}
