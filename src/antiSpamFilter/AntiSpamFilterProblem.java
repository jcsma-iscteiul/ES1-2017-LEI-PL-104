package antiSpamFilter;

import java.util.Arrays;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	
	private ReadConfiguration rc;
	private LogReader spam;
	private LogReader ham;

	public AntiSpamFilterProblem(ReadConfiguration rc, LogReader spam, LogReader ham) {
		this.rc = rc;
		this.spam = spam;
		this.ham = ham;
		
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
		
		

		solution.setObjective(0, FN);
		solution.setObjective(1, FP);
	}
}
