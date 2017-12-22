package Utils;

import java.util.HashMap;

import Readers.LogReader;
import Readers.ReadConfiguration;

/**
 * Calculator for FN and FP values based on rules.cf file and the spam and ham log files
 */
public class DetectionCalculator {

	private ReadConfiguration rc;
	private LogReader lrSPAM;
	private LogReader lrHAM;

	/**
	 * 
	 * @param rc Configuration read from file
	 * @param lrSPAM SPAM reader
	 * @param lrHAM HAM reader
	 */
	public DetectionCalculator(ReadConfiguration rc, LogReader lrSPAM, LogReader lrHAM) {
		this.rc = rc;
		this.lrSPAM = lrSPAM;
		this.lrHAM = lrHAM;
	}

	/**
	 * @return FP value calculated with the rules weight and the spam.log values
	 */
	public int calculateFP() {
		HashMap<String, String[]> msgHash = lrSPAM.getMsgRules();
		int FP = 0;
		double msgTotalWeight = 0;
		for(String i : msgHash.keySet()) { //iterar as mensagens
			for(int x = 0;x<msgHash.get(i).length;x++) { //iterar as regras das mensagens
				msgTotalWeight = msgTotalWeight + Double.parseDouble((rc.getConfiguration().get(msgHash.get(i)[x])));
			}
			if(msgTotalWeight<=5) {
				FP++;
			}
			msgTotalWeight = 0;
		}
		return FP;
	}

	/**
	 * @return FN value calculated with the rules weight and the ham.log values
	 */
	public int calculateFN() {
		HashMap<String, String[]> msgHash = lrHAM.getMsgRules();
		int FN = 0;
		double msgTotalWeight = 0;
		for(String i : msgHash.keySet()) { //iterar as mensagens
			for(int x = 0;x<msgHash.get(i).length;x++) { //iterar as regras das mensagens
				msgTotalWeight = msgTotalWeight + Double.parseDouble((rc.getConfiguration().get(msgHash.get(i)[x])));
			}
			if(msgTotalWeight>5) {
				FN++;
			}
			msgTotalWeight = 0;
		}
		return FN;
	}

}
