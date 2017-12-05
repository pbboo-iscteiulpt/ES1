package antiSpamFilter;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GUI {

	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	JLabel rules;
	JLabel ham;
	JLabel spam;
	JTextField text1;
	JTextField text2;
	JTextField text3;
	JCheckBox rulescheck;
	JCheckBox hamcheck;
	JCheckBox spamcheck;
	
	//Objetos do painel2
	private JTextField ManFN;
	private JTextField ManFP;
	private JList<Rule> ManList;
	
		GUI(){
			// Construtos para incializar a GUI 
			frame= new JFrame("Anti-Spam Filter");
			init();
			frame.setVisible(true);		
		}
		
		public void init(){
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new GridLayout(3,1));
			frame.setSize(470, 650);
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
			montarPanel2();
			frame.add(panel2);
				
		}
		
		private void montarPanel2() {

			panel2 = new JPanel();
			panel2.setLayout(null);
			panel2.setBorder(new LineBorder(new Color(0, 0, 0), 1));

			JButton AvaliarMan = new JButton("Avaliar");
			AvaliarMan.setBounds(300, 27, 89, 23);
			panel2.add(AvaliarMan);

			JButton GravarMan = new JButton("Gravar");
			GravarMan.setBounds(300, 72, 89, 23);
			panel2.add(GravarMan);

			DefaultListModel<Rule> model = new DefaultListModel<Rule>();
			ManList = new JList<Rule>(model);
			JScrollPane scrollPane = new JScrollPane(ManList);
			scrollPane.setBounds(10, 10, 250, 130);
			//scrollPane.setBackground(new Color(240,240,240));
			panel2.add(scrollPane);

			JLabel FN = new JLabel("FN:");
			FN.setBounds(21, 145, 57, 23);
			panel2.add(FN);

			JLabel FP = new JLabel("FP:");
			FP.setBounds(21, 170, 57, 23);
			panel2.add(FP);

			ManFN = new JTextField();
			ManFN.setBounds(64, 145, 86, 20);
			ManFN.setColumns(10);
			ManFN.setEditable(false);
			panel2.add(ManFN);

			ManFP = new JTextField();
			ManFP.setColumns(10);
			ManFP.setBounds(64, 170, 86, 20);
			ManFP.setEditable(false);
			panel2.add(ManFP);


		}

}
