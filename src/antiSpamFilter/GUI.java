package antiSpamFilter;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {

	private JFrame frame;
	private JPanel panel1;
	JLabel rules;
	JLabel ham;
	JLabel spam;
	JTextField text1;
	JTextField text2;
	JTextField text3;
	JCheckBox rulescheck;
	JCheckBox hamcheck;
	JCheckBox spamcheck;
	
	
		GUI(){
			// Construtos para incializar a GUI 
			frame= new JFrame("Anti-Spam Filter");
			init();
			frame.setVisible(true);		
		}
		
		public void init(){
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new GridLayout(3,1));
			frame.setSize(500, 500);
			frame.setLocationRelativeTo(null);
			
			panel1= new JPanel();
			panel1.setLayout(new GridLayout(3,3));
			
			rules= new JLabel("PATH rules.cf");
			ham= new JLabel("PATH ham.log");
			spam= new JLabel("PATH spam.log");
			
			text1= new JTextField();
			text1.setBackground(Color.WHITE);
			text1.setColumns(30);
			
			text2= new JTextField();
			text2.setBackground(Color.WHITE);
			text2.setColumns(30);
			
			text3= new JTextField();
			text3.setBackground(Color.WHITE);
			text3.setColumns(30);
			
			rulescheck= new JCheckBox();
			hamcheck= new JCheckBox();
			spamcheck= new JCheckBox();
			
			panel1.add(rules);
			panel1.add(text1);
			panel1.add(rulescheck);
			panel1.add(ham);
			panel1.add(text2);
			panel1.add(hamcheck);
			panel1.add(spam);
			panel1.add(text3);
			panel1.add(spamcheck);
			
			frame.add(panel1);
				
		}
}
