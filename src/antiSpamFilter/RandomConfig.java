package antiSpamFilter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomConfig {

	private String path = "";
	private ArrayList<Rule> rules;

	/***
	 * This class is used to apply a random config to the file rules.cf.
	 * 
	 * @param path Path to the file rules.cf
	 * 
	 * @author atmgo-iscteiul
	 */
	public RandomConfig(String path) {
		this.rules = new ArrayList<Rule>();
		this.path = path;
		addRandomWeight();
		writeConfig(rules);
	}

	/***
	 * Generate a random number from min to max.
	 * 
	 * @param min value
	 * @param max value
	 */
	private int generateRandom(int min, int max) {
		Random random = new Random();
		return random.nextInt((max-min)+1)-5;
	}

	/***
	 * Writes the rules with random weight to rules.cf.
	 * 
	 * @param rules Array with weight rules
	 */
	private void writeConfig(ArrayList<Rule> rules) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(path);
			for(Rule r : rules) {
				writer.print(r);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * Match each rule with respective random weight
	 * 
	 */
	private void addRandomWeight(){
		Scanner s;
		try {
			s = new Scanner(new File(path));
			while(s.hasNextLine()) {
				String l = s.nextLine();
				l = l.split(" ")[0];
				int weight = generateRandom(-5, 5);
				rules.add(new Rule(l,weight));
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * Get the list of rules and respective weights after the random configuration as been applied.
	 * 
	 * @return
	 */
	public ArrayList<Rule> getRuleList() {
		return rules;
	}

}
