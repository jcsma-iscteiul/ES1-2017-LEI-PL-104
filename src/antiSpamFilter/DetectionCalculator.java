package antiSpamFilter;

import java.util.ArrayList;
import java.util.HashMap;

public class DetectionCalculator {

	private RandomConfig rc;
	private LogReader lrSPAM;
	private LogReader lrHAM;

	public DetectionCalculator(RandomConfig rc, LogReader lrSPAM, LogReader lrHAM) {
		this.rc = rc;
		this.lrSPAM = lrSPAM;
		this.lrHAM = lrHAM;b
	}

	public int calculateFP() {
		HashMap<String, String[]> msgHash = lrSPAM.getMsgRules();
		ArrayList<Rule> weightRules = rc.getRuleList();
		int FP = 0;
		int msgTotalWeight = 0;
		for(String i : msgHash.keySet()) { //iterar as mensagens
			for(int x = 0;x<msgHash.get(i).length;x++) { //iterar as regras das mensagens
				for(int y = 0;y<weightRules.size();y++) { // iterar as regras
					if(msgHash.get(i)[x].equals(weightRules.get(y).getRule())) {
						msgTotalWeight = msgTotalWeight + weightRules.get(y).getWeight();
					}
				}
			}
			if(msgTotalWeight<=5) {
				FP++;
			}
			msgTotalWeight = 0;
		}
		return FP;
	}

	public int calculateFN() {
		HashMap<String, String[]> msgHash = lrHAM.getMsgRules();
		ArrayList<Rule> weightRules = rc.getRuleList();
		int FN = 0;
		int msgTotalWeight = 0;
		for(String i : msgHash.keySet()) { //iterar as mensagens
			for(int x = 0;x<msgHash.get(i).length;x++) { //iterar as regras das mensagens
				for(int y = 0;y<weightRules.size();y++) { // iterar as regras
					if(msgHash.get(i)[x].equals(weightRules.get(y).getRule())) {
						msgTotalWeight = msgTotalWeight + weightRules.get(y).getWeight();
					}
				}
			}
			if(msgTotalWeight>5) {
				FN++;
			}
			msgTotalWeight = 0;
		}
		return FN;
	}

}
