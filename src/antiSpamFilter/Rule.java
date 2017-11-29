package antiSpamFilter;

public class Rule {

	private String name;
	private int value;
	
	public Rule(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public Rule(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "  " + value ;
	}
	
	
	
	
}
