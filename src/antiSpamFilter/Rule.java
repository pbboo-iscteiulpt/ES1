package antiSpamFilter;

public class Rule {

	private String name;
	private double value;
	
	public Rule(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	public Rule(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double rr) {
		this.value = rr;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "	" + value ;
	}
	
	
	
	
}
