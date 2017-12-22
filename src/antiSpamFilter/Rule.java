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

	/**
	 * Obter o valor da regra
	 * @return Valor da regra
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Atualizar o valor da regra
	 * @param rr - Valor da regra
	 */
	public void setValue(double rr) {
		this.value = rr;
	}

	//Obter o nome da regra
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "  " + value ;
	}
	
	
	
	
}
