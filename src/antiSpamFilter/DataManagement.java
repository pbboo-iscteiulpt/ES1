package antiSpamFilter;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;

/**
 * @author Utilizador
 *
 */
public class DataManagement {

	private static List<Rule> rules;
	private static List<Email> spam;
	private static List<Email> ham;
	private GUI gui;


	public DataManagement() {
		rules = new ArrayList<Rule>();
		spam = new ArrayList<Email>();
		ham = new ArrayList<Email>();
	}

	/**
	 * Função para preencher um lista de rules, dada a path do ficheiro onde as mesmas se encontram
	 * @param path - Caminho para o ficheiro
	 */
	public static void fillRules(String path) {
		try {
			Scanner s = new Scanner(new File(path));

			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] v = line.split("	");

				Rule r = new Rule(v[0]);

				if(v.length==2) {
					
					r.setValue(Double.parseDouble(v[1]));

				}

				rules.add(r);
			}

			s.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 	Função para preencher um lista de Emails que são SPAM, dada a path do ficheiro onde os mesmos se encontram.
	 * @param path - Caminho para o ficheiro
	 */
	public static void fillSpam(String path) {

		try {
			Scanner s = new Scanner(new File(path));

			while(s.hasNextLine()) {

				String line = s.nextLine();
				String[] v = line.split("	");

				Email e = new Email(v[0]);

				for(int i=1; i< v.length; i++) {
					e.addTag(v[i]);

				}
				spam.add(e);
			}

			s.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Função para preencher um lista de Emails que são HAM, dada a path do ficheiro onde os mesmos se encontram. 
	 * @param path - Caminho para o ficheiro
	 */
	public static void fillHam(String path) {

		try {
			Scanner s = new Scanner(new File(path));

			while(s.hasNextLine()) {

				String line = s.nextLine();
				String[] v = line.split("	");

				Email e = new Email(v[0]);

				for(int i=1; i< v.length; i++) {
					e.addTag(v[i]);	
				}	
				ham.add(e);
			}

			s.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Função de obtenção da lista de rules
	 * @return Lista de regras
	 */
	public static List<Rule> getRules() {
		return rules;
	}

	/**
	 * Função de obtenção da lista de Emails SPAM
	 * @return Lista de ficheiros que estão no spam.log
	 */
	public static List<Email> getSpam() {
		return spam;
	}

	/**
	 * Função de obtenção da lista de Emails HAM
	 * @return Lista de ficheiros que estão no ham.log
	 */
	public static List<Email> getHam() {
		return ham;
	}

	/**
	 * Função para classificar as mensagens como HAM ou SPAM
	 * @param s - Booleano que indica se a path do spam.log é avaliada (se a respetiva checkbox está selecionada)
	 * @param h - Booleano que indica se a path do ham.log é avaliada (se a respetiva checkbox está selecionada)
	 * @param FN - Número de FN (mensagens SPAM consideradas HAM)
	 * @param FP - Número de FP (mensagens HAM consideradas SPAM)
	 * @param rules - Lista de regras recebida
	 */
	public static void avaliar(boolean s, boolean h, JTextField FN, JTextField FP, List<Rule> rules) {

		ArrayList<Email> temp = new ArrayList<Email>();


		if(s==true) {
			temp.addAll(spam);
		}
		if(h==true) {
			temp.addAll(ham);
		}

		for(Email e:temp) {
			double a=0;
			for(String p: e.getTags()) {
				for(Rule r:rules) {
					if(p.equals(r.getName())) {
						a = a + r.getValue();
					}
				}
			}
			if(a>=5) {
				e.setType("SPAM");
			}
			else {
				e.setType("HAM");
			}

		}

		setFN(temp, FN);
		setFP(temp, FP);

	}

	/**
	 * Função para calcular os FN
	 * @param temp - Lista de Emails recebida
	 * @param FN - Número de FN (mensagens SPAM consideradas HAM)
	 */
	public static void setFN(List<Email> temp,JTextField FN){
		int fn = 0;
		for(Email e : temp){
			for(Email m: ham){
				if((e.getName().equals(m.getName()))&&(e.getType().equals("SPAM"))){
					fn++;
				}
			}
		}
		FN.setText(String.valueOf(fn));
	}

	/**
	 * 	Função para calcular os FN
	 * @param temp - Lista de Emails recebida
	 * @param FP - Número de FP (mensagens HAM consideradas SPAM)
	 */
	public static void setFP(List<Email> temp,JTextField FP) {
		int fp=0;
		for(Email e: temp) {
			for(Email s:spam) {
				if(e.getName().equals(s.getName()) && e.getType().equals("HAM")) {
					fp++;
				}
			}
		}

		FP.setText(String.valueOf(fp));
	}
	
	/**
	 * Função para guardar as regras e respetivos valores 
	 * @param list - Lista de regras recebida
	 */
	public static void Save(List <Rule> list){

		try {

			BufferedWriter pw;
			try {
				pw = new BufferedWriter(new FileWriter("ficheiros/rules.cf"));

				for(Rule r: list){
					pw.write(r.toString());
					pw.newLine();
				}
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}finally {}
	}

}

