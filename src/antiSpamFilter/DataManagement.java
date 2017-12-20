package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;

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
	
	public static void fillRules(String path) {
		try {
			Scanner s = new Scanner(new File(path));
			
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String[] v = line.split("	");
				
				Rule r = new Rule(v[0]);
				
				if(v.length==2) {
					r.setValue(Integer.parseInt(v[1]));
				}
				
				rules.add(r);
			}
			
			s.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
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

	public static List<Rule> getRules() {
		return rules;
	}

	public static List<Email> getSpam() {
		return spam;
	}

	public static List<Email> getHam() {
		return ham;
	}
	
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

		setFN(FN,temp);
		setFP(FP,temp);

	}
	
	public static void setFN(JTextField FN, List<Email> temp){
		int fn = 0;
		for(Email e : temp){
			for(Email m: ham){
				if((e.getName().equals(m.getName()))&&(e.getType().equals("SPAM"))){
					fn++;
				}
			}
		}
		FN.setText("");
		FN.setText(Integer.toString(fn));
	}
	
	private static void setFP(JTextField FP, List<Email> temp) {


		int fp=0;
		for(Email e: temp) {
			for(Email s:spam) {
				if(e.getName().equals(s.getName()) && e.getType().equals("HAM")) {
					fp++;
				}
			}
		}

		FP.setText("");
		FP.setText(Integer.toString(fp));
	}
	
	public static void Save(List <Rule> list){
		
		try {
			PrintWriter pw= new PrintWriter(new File("ficheiros/rules.cf"));
			for(Rule r: list){
				pw.println(r.toString());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}


