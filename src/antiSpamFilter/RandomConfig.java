package antiSpamFilter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class RandomConfig {

	String path = "";

	/***
	 * This class is used to apply a random config to the file rules.cf.
	 * 
	 * @param path Path to the file rules.cf
	 * 
	 * @author atmgo-iscteiul
	 */
	public RandomConfig(String path) {
		this.path = path;
		applyRandomConfig();
	}

	/***
	 * Generate a random number from min to max.
	 * 
	 * @param min value
	 * @param max value
	 */
	//Generate a number from -5 to 5
	private int generateRandom(int min, int max) {
		Random random = new Random();
		return random.nextInt((max-min)+1)-5;
	}

	/***
	 * Write to rules.cf each rule with the random weight.
	 * 
	 * @param path
	 */
	//Adds random weights to each of the rules in rules.cf file
	public void applyRandomConfig(){
		String fileToOutput = "";
		Scanner s;
		try {
			s = new Scanner(new File(path));
			while(s.hasNextLine()) {
				String l = s.nextLine();
				l = l.split(" ")[0];
				int weight = generateRandom(-5, 5);
				l = l + " " + weight + "\n";
				fileToOutput = fileToOutput + l;
			}
			s.close();
			PrintWriter writer = new PrintWriter(path);
			writer.print(fileToOutput);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
