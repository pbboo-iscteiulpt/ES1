package antiSpamFilter;


import java.util.ArrayList;
import java.util.List;


public class Email {

	//Atributos do Email
	private String name;
	private List<String> tags;
	private String type;
	
	//Construtor do Email, dado um nome do mesmo.
	public Email(String name) {
		this.name = name;
		tags = new ArrayList<String>();
	}
	
	
	/**
	 * Função de adicionar tags à lista de tags do Email
	 * @param tag - Tag do Email
	 */
	public void addTag(String tag) {
		tags.add(tag);
	}

	
	/**
	 * Função de obter a lista de tags do Email
	 * @return Lista de tags do Email
	 */
	public List<String> getTags() {
		return tags;
	}
	
	
	/**
	 * Função de obter o nome do Email
	 * @return Nome do Email
	 */
	public String getName() {
		return name;
	}

	/**
	 * Função para classificar o Email como SPAM ou HAM
	 * @param type - Tipo de Email (SPAM ou HAM)
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * Função de obter o tipo de Email(SPAM ou HAM)
	 * @return Tipo de Email (SPAM ou HAM)
	 */
	public String getType() {
		return type;
	}

	
}
