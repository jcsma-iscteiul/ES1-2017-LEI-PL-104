package antiSpamFilter;

public class Rule {
	
	private String rule;
	private int weight;
	
	public Rule(String rule, int weight) {
		this.rule = rule;
		this.weight = weight;
	}
	
	public String getRule() {
		return rule;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return rule + " " + weight;
	}

}
