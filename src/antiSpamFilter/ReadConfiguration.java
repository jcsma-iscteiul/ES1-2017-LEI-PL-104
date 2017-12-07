package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadConfiguration {

	private String path;
	private HashMap<String, String> rulesWeight;

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
					rulesWeight = null;
					break;
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getConfiguration() {
		if(rulesWeight != null) {
			return rulesWeight;
		}else {
			return null;
		}
		
	}

	public static void main(String[] args) {
		ReadConfiguration r = new ReadConfiguration("C:\\Users\\Adolfo\\Documents\\ES Proj Files\\rules.cf");
		HashMap<String, String> config = r.getConfiguration();
	}

}
