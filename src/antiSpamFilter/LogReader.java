package antiSpamFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LogReader {

	private HashMap<Integer, ArrayList<Rule>> msgRules = new HashMap<Integer, ArrayList<Rule>>();
	private Scanner s;

	/***
	 * Classed used to parse the log files and use the content.
	 * 
	 * @author atgmo-iscteiul
	 * 
	 * @param path
	 */
	public LogReader(String path) {
		s = new Scanner(path);
	}

	/***
	 * Opens the log file and saves the content to a HashMap<Integer, ArrayList<Rule> using the msg as key to get the rules.
	 * 
	 */
	public void read() {
		while(s.hasNextLine()) {
			String l = s.nextLine();
			String[] line = l.split(" ");
			Integer msg = Integer.parseInt(line[0]);
			String[] rules = new String[line.length-1];
			for(int i = 1;i<line.length;i++) {
				rules[i-1] = line[i];
			}
		}
	}


}
