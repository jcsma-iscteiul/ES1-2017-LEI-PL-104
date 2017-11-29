package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LogReader {

	private HashMap<String, String[]> msgRules;
	private Scanner s;
	private String path;

	/***
	 * Classed used to parse the log files and use the content.
	 * 
	 * @author atgmo-iscteiul
	 * 
	 * @param path
	 */
	public LogReader(String path) {
		this.path = path;
		msgRules = new HashMap<String, String[]>();
		read();
	}

	/***
	 * Opens the log file and saves the content to a HashMap<String, String[]> using the msgID as key to get the rules.
	 * 
	 */
	private void read() {
		try {
			s = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(s.hasNextLine()) {
			String l = s.nextLine();
			String[] line = l.split("\t");
			String id = line[0];
			String[] rules = new String[line.length-1];
			for(int i = 1;i<line.length;i++) {
				rules[i-1] = line[i];
			}
			msgRules.put(id, rules);
		}
	}
	
	public HashMap<String, String[]> getMsgRules() {
		return msgRules;
	}
	
}
