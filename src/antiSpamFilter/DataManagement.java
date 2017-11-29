package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManagement {
	
	private static List<Rule> rules;
	private static List<Email> spam;
	private static List<Email> ham;
	
	
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
	
}
