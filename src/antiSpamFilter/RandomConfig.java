package antiSpamFilter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RandomConfig {
	
	String path = "";

	public RandomConfig() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				".cf Files Only", "cf");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(chooser);
		//escolher o caminho para o rules.cf
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getAbsolutePath();
		}
	}
	
	//devolve o caminho para o rules.cf
	public String getPath() {
		return path;
	}

	//Generate a number from -5 to 5
	public int generateRandom(int min, int max) {
		Random random = new Random();
		return random.nextInt((max-min)+1)-5;
	}

	//Adds random weights to each of the rules in rules.cf file
	public void writeToFile(String path) throws FileNotFoundException {
		String fileToOutput = "";
		Scanner s = new Scanner(new File(path));
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
	}


	public static void main(String[] args) throws FileNotFoundException {
		RandomConfig x = new RandomConfig();
		x.writeToFile(x.getPath());
	}
}
