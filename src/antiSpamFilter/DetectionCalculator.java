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
		this.lrHAM = lrHAM;
	}

	public int calculateFP() {
		HashMap<String, String[]> msgHash = lrSPAM.getMsgRules();
		ArrayList<Rule> weightRules = rc.getRuleList();
		int FP = 0;
		
		return FP;
	}

	public int calculateFN() {
		HashMap<String, String[]> msgHash = lrHAM.getMsgRules();
		ArrayList<Rule> weightRules = rc.getRuleList();
		int FN = 0;
		
		return FN;
	}

}
