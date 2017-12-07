package antiSpamFilter;

import java.util.HashMap;

public class DetectionCalculator {

	private ReadConfiguration rc;
	private LogReader lrSPAM;
	private LogReader lrHAM;

	public DetectionCalculator(ReadConfiguration rc, LogReader lrSPAM, LogReader lrHAM) {
		this.rc = rc;
		this.lrSPAM = lrSPAM;
		this.lrHAM = lrHAM;
	}

	public int calculateFP() {
		HashMap<String, String[]> msgHash = lrSPAM.getMsgRules();
		int FP = 0;
		int msgTotalWeight = 0;
		for(String i : msgHash.keySet()) { //iterar as mensagens
			for(int x = 0;x<msgHash.get(i).length;x++) { //iterar as regras das mensagens
				msgTotalWeight = msgTotalWeight + Integer.parseInt((rc.getConfiguration().get(msgHash.get(i)[x])));
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
		int FN = 0;
		int msgTotalWeight = 0;
		for(String i : msgHash.keySet()) { //iterar as mensagens
			for(int x = 0;x<msgHash.get(i).length;x++) { //iterar as regras das mensagens
				msgTotalWeight = msgTotalWeight + Integer.parseInt((rc.getConfiguration().get(msgHash.get(i)[x])));
			}
			if(msgTotalWeight>5) {
				FN++;
			}
			msgTotalWeight = 0;
		}
		return FN;
	}

}
