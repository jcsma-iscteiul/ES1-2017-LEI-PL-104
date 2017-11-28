package antiSpamFilter;

public class Rule {
	
	private String rule;
	private int weight;
	
	/***
	 * This class represents a rule and its respective weight.
	 * 
	 * @author atmgo-iscteiul
	 *
	 * @param rule name of the rule
	 * @param weight weight of the rule
	 */
	public Rule(String rule, int weight) {
		this.rule = rule;
		this.weight = weight;
	}
	
	
	/***
	 * Returns the name of the rule.
	 * 
	 */
	public String getRule() {
		return rule;
	}
	
	
	/***
	 * Returns the weight of the rule.
	 * 
	 */
	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return rule + " " + weight;
	}

}
