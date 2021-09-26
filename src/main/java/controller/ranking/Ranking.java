package controller.ranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class Ranking {

	private Map<String, Integer> rankingMap;
	private File rankingFile;

	public Ranking() throws ClassNotFoundException, IOException {
		this.rankingMap = new HashMap<>();
		System.out.println("path loading");
		this.rankingFile = new File("Rank.txt");
		System.out.println("path loaded");
		this.loadMapFromFile();
	}

	@SuppressWarnings("unchecked")
	public void loadMapFromFile() throws IOException, ClassNotFoundException {
		if (!this.rankingFile.exists()) {
			System.out.println("File not exists");
			System.out.println(this.rankingFile.createNewFile());
			this.saveMapToFile();
			System.out.println("File createdd 12");
			return;
		}
//		System.out.println("File not createdd 3");
//		FileInputStream fileIn = new FileInputStream(this.rankingFile);
//		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
//		this.rankingMap = (Map<String, Integer>) objectIn.readObject();
//		objectIn.close();
	}

	private void saveMapToFile() throws IOException {
		Properties properties = new Properties();
		for (Map.Entry<String,Integer> entry : this.rankingMap.entrySet()) {
		    properties.put(entry.getKey(), entry.getValue().toString());
		}
		properties.store(new FileOutputStream(this.rankingFile), null);
		System.out.println("map saved on file");
	}
	
	public void addToMap(String name, Integer points) throws IOException {
		this.rankingMap.put(name, points);
		this.saveMapToFile();
	}

}
