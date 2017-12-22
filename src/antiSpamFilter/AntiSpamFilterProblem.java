package antiSpamFilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Readers.LogReader;
import Readers.ReadConfiguration;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	
	private ReadConfiguration rc;
	private LogReader lrSPAM;
	private LogReader lrHAM;

	public AntiSpamFilterProblem(ReadConfiguration rc, LogReader lrSPAM, LogReader lrHAM) {
		this.rc = rc;
		this.lrSPAM = lrSPAM;
		this.lrHAM = lrHAM;
		
		setNumberOfVariables(rc.getConfiguration().size());
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		Double[] lower = new Double[getNumberOfVariables()];
		Arrays.fill(lower,-5.0);
		Double[] upper = new Double[getNumberOfVariables()];
		Arrays.fill(upper, 5.0);
		List<Double> lowerLimit = Arrays.asList(lower);
		List<Double> upperLimit = Arrays.asList(upper);
		
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	public void evaluate(DoubleSolution solution){
		int FP = 0;
		int FN = 0;
		double[] nSolution = new double[solution.getNumberOfVariables()];
		
		for(int x = 0;x< solution.getNumberOfVariables();x++) {
			nSolution[x] = solution.getVariableValue(x);
		}
		
		//FP
		HashMap<String, String[]> msgHash = lrSPAM.getMsgRules();
		for(String i : msgHash.keySet()) { //iterar as mensagens
			int msgTotalWeight = 0;
			for(int x = 0;x<msgHash.get(i).length;x++) { //iterar as regras das mensagens
				msgTotalWeight += nSolution[x];
			}
			if(msgTotalWeight<5) {
				FP++;
			}
		}
		
		//FN
		HashMap<String, String[]> msgHashHam = lrHAM.getMsgRules();
		for(String i : msgHashHam.keySet()) { //iterar as mensagens
			int msgTotalWeight = 0;
			for(int x = 0;x<msgHashHam.get(i).length;x++) { //iterar as regras das mensagens
				msgTotalWeight += nSolution[x];
			}
			if(msgTotalWeight>5) {
				FN++;
			}
		}
		
		solution.setObjective(0, FN);
		solution.setObjective(1, FP);
	}
}
