package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ReadConfiguration {

	private String path;
	private HashMap<String, String> rulesWeight;
	private boolean read = true;

	public ReadConfiguration(String path) {
		this.path = path;
		readFile(path);
	}

	private void readFile(String path) {
		Scanner s;
		rulesWeight = new HashMap<String, String>();
		try {
			s = new Scanner(new File(path));
			while(s.hasNextLine()) {
				String l = s.nextLine();
				if(l.split(" ").length != 1) {
					rulesWeight.put(l.split(" ")[0], l.split(" ")[1]);
				}else {
					read = false;
					rulesWeight.put(l, "");
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getConfiguration() {
		return rulesWeight;
	}

	private int generateRandom(int min, int max) {
		Random random = new Random();
		return random.nextInt((max-min)+1)-5;
	}

	private void generateRandomConfig() {
		for(String i : rulesWeight.keySet()) {
			rulesWeight.put(i, String.valueOf(generateRandom(-5,5)));
		}
	}

	public void applyRandomConfig() {
		generateRandomConfig();
		PrintWriter writer;
		try {
			writer = new PrintWriter(path);
			for(String i : rulesWeight.keySet()) {
				String l = i + " " + rulesWeight.get(i) + "\n";
				writer.print(l);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getWasTherePreviousConfig() {
		return read;
	}

}
