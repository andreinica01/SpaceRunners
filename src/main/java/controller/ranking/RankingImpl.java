package controller.ranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Ranking System Manager.
 */
public class RankingImpl implements Ranking {

    private Map<String, Integer> map;
    private File file;
    private String filePathname;

    /**
     * Constructor.
     * 
     * @throws IOException
     */
    public RankingImpl() throws IOException {
        this.map = new HashMap<>();
        this.filePathname = "src/main/resources/ranking/Ranking.txt";
        this.file = new File(this.filePathname);
        this.loadFromFile();
    }

    /**
     * Load scores info from file. If file doesn't exists, create a new one.
     * 
     * @throws IOException
     */
    private void loadFromFile() throws IOException {
        if (this.file.exists()) {
            Properties p = new Properties();
            p.load(new FileInputStream(this.file));
            p.stringPropertyNames().forEach(e -> this.map.put(e, Integer.valueOf(p.get(e).toString())));
        } else {
            this.file.createNewFile();
            this.saveToFile();
        }
    }

    /**
     * Saves scores info to file.
     * 
     * @throws IOException
     */
    private void saveToFile() throws IOException {
        Properties p = new Properties();
        this.map.entrySet().forEach(e -> p.put(e.getKey(), e.getValue().toString()));
        p.store(new FileOutputStream(this.file), null);
    }

    /**
     * Add a new player's score to the ranking system.
     * 
     * @param playerName
     * @param playerScore
     * @throws IOException
     */
    @Override
    public void addPlayer(final String playerName, final Integer playerScore) throws IOException {
        if (this.map.containsKey(playerName) && (playerScore <= this.map.get(playerName))) {
            return;
        }
        this.map.put(playerName, playerScore);
        this.saveToFile();
    }

    /**
     * Reverse sort a Map<String, Integer> into a List<Entry<String, Integer> by
     * value. Example: Map{(a,5), (b,1), (c,10)} -> List{(c,10), (a,5), (b,1)}
     * 
     * @param unsortedMap map to sort
     * @return an ordered list containing the sorted entries of the map
     */
    private List<Entry<String, Integer>> getSortedEntryList(final Map<String, Integer> unsortedMap) {
        return unsortedMap.entrySet().stream().sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    /**
     * Return a formatted String containing a limited part of the information of
     * this ranking map.
     * 
     * @return a formatted String
     */
    @Override
    public String getFormattedRankingMap(int limit) {
        StringJoiner s = new StringJoiner("");
        List<Entry<String, Integer>> sortedEntry;
        sortedEntry = this.getSortedEntryList(this.map).stream().limit(limit).collect(Collectors.toList());
        sortedEntry.forEach(e -> s.add(e.getValue().toString() + "\t" + e.getKey() + "\n"));
        return s.toString();
    }

}
