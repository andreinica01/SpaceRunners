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

public class Ranking {

	private Map<String, Integer> rankingMap;
	private File rankFile;

	public Ranking() throws  IOException {
		this.rankingMap = new HashMap<>();
		this.rankFile = new File("Rank.txt");
		this.loadMapFromFile();
	}

	public void loadMapFromFile() throws IOException {
		if (!this.rankFile.exists()) {
			this.rankFile.createNewFile();
			this.saveMapToFile();
		} else {
			Properties properties = new Properties();
			properties.load(new FileInputStream(this.rankFile));
			for (String key : properties.stringPropertyNames()) {
				this.rankingMap.put(key, Integer.valueOf(properties.get(key).toString()));
			}
		}
	}

	private void saveMapToFile() throws IOException {
		Properties properties = new Properties();
		for (Entry<String, Integer> entry : this.rankingMap.entrySet()) {
			properties.put(entry.getKey(), entry.getValue().toString());
		}
		properties.store(new FileOutputStream(this.rankFile), null);
	}

	public void addToMap(String name, Integer points) throws IOException {
		this.rankingMap.put(name, points);
		this.saveMapToFile();
	}

	/**
	 * Reverse sort a Map<String,Integer> by value. ex: Map{(a,5), (b,1), (c,10)} ->
	 * Map{(c,10), (a,5), (b,1)}
	 * 
	 * @param Map<String,Integer>
	 * @return List<Entry<String, Integer>>
	 */
	private List<Entry<String, Integer>> reverseSortMap(Map<String, Integer> unsortedMap) {
		return unsortedMap.entrySet().stream().sorted(Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toList());
	}

	public Map<String, Integer> getRankingMap() {
		return this.rankingMap;
	}

	public String getMapToString() {
		StringJoiner s = new StringJoiner("");
		List<Entry<String, Integer>> sortedEntry = this.reverseSortMap(this.rankingMap).stream().limit(5)
				.collect(Collectors.toList());
		for (Entry<String, Integer> entry : sortedEntry) {
			s.add(entry.getValue().toString() + "\t");
			s.add(entry.getKey() + "\n");
		}
		return s.toString();
	}

}
