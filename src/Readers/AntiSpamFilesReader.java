package Readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AntiSpamFilesReader {
	
	private String fileRF;
	private String fileRS;
	private ArrayList<Double> FP = new ArrayList<Double>();
	private ArrayList<Double> FN = new ArrayList<Double>();
	private ArrayList<String> RS = new ArrayList<String>();
	
	public AntiSpamFilesReader(String fileRF, String fileRS) {
		this.fileRF = fileRF;
		this.fileRS = fileRS;
		readRF();
		readRS();
		
	}
	
	//Melhor FP e FN Combo (linha)
	public void readRF() {
		try {
			Scanner s = new Scanner(new File(fileRF));
			while(s.hasNextLine()) {
				String l = s.nextLine();
				FN.add(Double.parseDouble(l.split(" ")[0]));
				FP.add(Double.parseDouble(l.split(" ")[1]));
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Consoante a linha melhor do readRF ir buscar o peso das regras dessa linha
	public void readRS() {
		try {
			Scanner s = new Scanner(new File(fileRS));
			while(s.hasNextLine()) {
				RS.add(s.nextLine());
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getRuleSet() {
		return RS;
	}
	
	public ArrayList<Double> getFN() {
		return FN;
	}
	
	public ArrayList<Double> getFP() {
		return FP;
	}
	

}
