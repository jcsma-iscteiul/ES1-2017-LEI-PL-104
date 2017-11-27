package antiSpamFilter;

import java.util.HashMap;
import java.util.Scanner;

public class LogReader {

	private HashMap<Integer, String[]> msgRules = new HashMap<Integer, String[]>();
	private Scanner s;


	public LogReader(String path) {
		s = new Scanner(path);
	}

	public void read() {
		while(s.hasNextLine()) {
			String l = s.nextLine();
			String[] line = l.split(" ");
			Integer msg = Integer.parseInt(line[0]);
			String[] rules = new String[line.length-1];
			for(int i = 1;i<line.length;i++) {
				rules[i-1] = line[i];
			}
			msgRules.put(msg,rules);
		}
	}

	public HashMap<Integer, String[]> getMsgRules() {
		return msgRules;
	}



}
