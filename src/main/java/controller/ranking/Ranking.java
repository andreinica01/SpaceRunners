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

import utilities.MagicEnumInt;

/**
 * Ranking System Manager.
 */
public class Ranking {

	private Map<String, Integer> map;
	private File file;

	/**
	 * Constructor.
	 * @throws IOException
	 */
	public Ranking() throws IOException {
		this.map = new HashMap<>();
		this.file = new File("src/main/resources/ranking/Ranking.txt");
		this.loadMapFromFile();
	}

	/**
	 * Load scores info from file. If file doesn't exists, create a new one.
	 * 
	 * @throws IOException
	 */
	private void loadMapFromFile() throws IOException {
		if (this.file.exists()) {
			Properties p = new Properties();
			p.load(new FileInputStream(this.file));
			p.stringPropertyNames()
			 .forEach(e -> this.map.put(e, Integer.valueOf(p.get(e).toString())));
		} else {
			this.file.createNewFile();
			this.saveMapToFile();
		}
	}

	/**
	 * Saves scores info to file.
	 * 
	 * @throws IOException
	 */
	private void saveMapToFile() throws IOException {
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
	public void addToMap(final String playerName, final Integer playerScore) throws IOException {
		this.map.put(playerName, playerScore);
		this.saveMapToFile();
	}

	/**
	 * Reverse sort a Map<String, Integer> into a List<Entry<String, Integer> by
	 * value. Ex: Map{(a,5), (b,1), (c,10)} -> List{(c,10), (a,5), (b,1)}
	 * 
	 * @param Map<String, Integer>
	 * @return List<Entry<String, Integer>>
	 */
	private List<Entry<String, Integer>> getSortedEntryList(final Map<String, Integer> unsortedMap) {
		return unsortedMap.entrySet().stream()
				.sorted(Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toList());
	}

	/**
	 * Return the Map of all players score.
	 * 
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getRankingMap() {
		return this.map;
	}

	@Override
	public final String toString() {
		StringJoiner s = new StringJoiner("");
		List<Entry<String, Integer>> sortedEntry;
		sortedEntry = this.getSortedEntryList(this.map).stream().limit(MagicEnumInt.TEN.getValue()).collect(Collectors.toList());
		sortedEntry.forEach(e -> s.add(e.getValue().toString() + "\t" + e.getKey() + "\n"));
		return s.toString();
	}

}
