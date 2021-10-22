package controller.ranking;

import java.io.IOException;

public interface Ranking {
    
    void addPlayer(final String playerName, final Integer playerScore) throws IOException;
    
    String getFormattedRankingMap(int limit);
}
