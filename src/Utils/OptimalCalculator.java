package Utils;

import java.util.ArrayList;
import java.util.Collections;

import Readers.AntiSpamFilesReader;
import Readers.ReadConfiguration;

public class OptimalCalculator {

	private ReadConfiguration rc;
	private AntiSpamFilesReader asfr;
	private String ruleSet;
	private int BestFset;

	public OptimalCalculator(ReadConfiguration rc, AntiSpamFilesReader asfr) {
		this.rc = rc;
		this.asfr = asfr;
		calculateBestFset();
		getRuleSet();
		writeBestRules();
	}

	public int calculateBestFset() { //1º
		ArrayList<Double> FP = asfr.getFP();
		ArrayList<Double> FN = asfr.getFN();
		ArrayList<Double> bestSet = new ArrayList<Double>();
		for(int x = 0;x < FP.size();x++) {
			bestSet.add(Math.sqrt(Math.pow(FP.get(x), 2)+ Math.pow(FN.get(x), 2)));
		}
		BestFset = bestSet.indexOf(Collections.min(bestSet));
		return BestFset;
	}

	public String getRuleSet() { //2º
		ruleSet = asfr.getRuleSet().get(BestFset);
		return ruleSet;
	}

	public void writeBestRules() { //3º
		String[] rulesWeight = ruleSet.split(" ");
		int i = 0;
		for(String rule : rc.getConfiguration().keySet()) {
			rc.getConfiguration().put(rule, rulesWeight[i]);
			i++;
		}
		rc.writeConfig();
	}

}
