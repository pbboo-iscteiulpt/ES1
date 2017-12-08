package antiSpamFilter;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;



public class GUI {

	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
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
	

	//Objetos do painel3
	private JTextField AutoFN;
	private JTextField AutoFP;
	private JList<Rule> AutoList = new JList<Rule>();
	private JTable AutoTable;
	
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
			montarPanel3();
			frame.add(panel3);
				
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
		
		private void montarPanel3() {

			panel3 = new JPanel();
			panel3.setLayout(null);
			panel3.setBorder(new LineBorder(new Color(0, 0, 0), 1));

			JButton GerarAuto = new JButton("Gerar");
			GerarAuto.setBounds(300, 27, 89, 23);
			GerarAuto.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
				}
			});
			panel3.add(GerarAuto);

			JButton GravarAuto = new JButton("Gravar");
			GravarAuto.setBounds(300, 72, 89, 23);
			panel3.add(GravarAuto);

			JButton AvaliarAuto = new JButton("Avaliar");
			AvaliarAuto.setBounds(300, 117, 89, 23);
			panel3.add(AvaliarAuto);

			//headers for the table
			String[] columns = new String[] {"Nome","Peso"};
			
			//create table with data
			DefaultTableModel AutoModel = new DefaultTableModel(null, columns);

			AutoTable = new JTable(AutoModel);

			JScrollPane AutoscrollPane = new JScrollPane(AutoTable);
			AutoscrollPane.setBounds(10, 10, 250, 130);
			panel3.add(AutoscrollPane);

					
//			DefaultListModel<Rule> model = new DefaultListModel<Rule>();
//			AutoList = new JList<Rule>(model);
			JScrollPane scrollPane = new JScrollPane(AutoList);
			scrollPane.setBounds(10, 10, 250, 130);
			//scrollPane.setBackground(new Color(240,240,240));
			panel3.add(scrollPane);

			JTable table = new JTable(336, 2);
			JScrollPane scrollpane = new JScrollPane(table);
			panel3.add(table);
			
			JLabel FN = new JLabel("FN:");
			FN.setBounds(21, 145, 57, 23);
			panel3.add(FN);

			JLabel FP = new JLabel("FP:");
			FP.setBounds(21, 170, 57, 23);
			panel3.add(FP);

			AutoFN = new JTextField();
			AutoFN.setBounds(64, 145, 86, 20);
			AutoFN.setColumns(10);
			AutoFN.setEditable(false);
			panel3.add(AutoFN);

			AutoFP = new JTextField();
			AutoFP.setColumns(10);
			AutoFP.setBounds(64, 170, 86, 20);
			AutoFP.setEditable(false);
			panel3.add(AutoFP);


		}


}
