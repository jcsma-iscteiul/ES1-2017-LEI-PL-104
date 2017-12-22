package Readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LogReader {

	private HashMap<String, String[]> msgRules;
	private Scanner s;
	private String path;

	/***
	 * Class used to parse the log files and use the content.
	 * 
	 * @param path path to the file that you want to read
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
			addContent(id, rules);
		}
	}


	/***
	 * Getter than returns the HashMap<String, String[]> msgRules
	 * 
	 * @return msgRules
	 */
	public HashMap<String, String[]> getMsgRules() {
		return msgRules;
	}


	/***
	 * Add content to the HashMap<String, String[]> msgRules
	 * 	 
	 * @param a email ID
	 * @param b rules of the email
	 */
	public void addContent(String a, String[] b) {
		msgRules.put(a, b);
	}

}
