package antiSpamFilter;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;



public class GUI {

	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JCheckBox rulescheck;
	private JCheckBox hamcheck;
	private JCheckBox spamcheck;

	//Objetos do painel2
	private JTextField ManFN;
	private JTextField ManFP;
	private List<Rule> ManList;
	private JTable ManTable;

	//Objetos do painel3
	private JTextField AutoFN;
	private JTextField AutoFP;
	private List<Rule> AutoList;
	private JTable AutoTable;


	GUI(){
		// Construtos para incializar a GUI 
		frame= new JFrame("Anti-Spam Filter");
		init();
		frame.setVisible(true);		

		ManList = new ArrayList<Rule>();
		AutoList = new ArrayList<Rule>();

	}

	public void init(){

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3,1));
		frame.setSize(470, 650);
		frame.setLocationRelativeTo(null);

		panel1= new JPanel();
		panel1.setLayout(new GridLayout(3,3));

		JLabel rules= new JLabel("PATH rules.cf");
		JLabel ham= new JLabel("PATH ham.log");
		JLabel spam= new JLabel("PATH spam.log");

		text1= new JTextField();
		text1.setBackground(Color.WHITE);
		text1.setColumns(30);
		
		
		text1.setText("ficheiros/rules.cf");

		text1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(! text1.getText().isEmpty()){
					String str1=text1.getText();
					//System.out.println(str1);
					DataManagement.fillRules(str1);

					for(Rule a : DataManagement.getRules()) {
						Rule b = new Rule(a.getName(), a.getValue());
						Rule c = new Rule(a.getName(), a.getValue());
						ManList.add(b);
						AutoList.add(c);
					}
					
					fillJtabel();  
				}
			}
		});

		text2= new JTextField();
		text2.setBackground(Color.WHITE);
		text2.setColumns(30);
		
		text2.setText("ficheiros/ham.log");

		text2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(! text2.getText().isEmpty()){
					String str2=text2.getText();
					DataManagement.fillHam(str2);
					System.out.println("passou2");
				}
			}
		});

		text3= new JTextField();
		text3.setBackground(Color.WHITE);
		text3.setColumns(30);
		
		text3.setText("ficheiros/spam.log");

		text3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(! text3.getText().isEmpty()){
					String str3=text3.getText();
					DataManagement.fillSpam(str3);
					System.out.println("passou3");
				}
			}
		});

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

		AvaliarMan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DataManagement.avaliar(spamcheck.isSelected(), hamcheck.isSelected(), ManFN, ManFP, ManList);

			}
		});

		panel2.add(AvaliarMan);

		JButton GravarMan = new JButton("Gravar");
		GravarMan.setBounds(300, 72, 89, 23);
		
		GravarMan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataManagement.Save(ManList);
				
			}
		});

		panel2.add(GravarMan);

		//headers for the table
		String[] columns = new String[] {"Nome","Peso"};

		DefaultTableModel ManModel = new DefaultTableModel(null, columns);

		ManTable = new JTable(ManModel);

		ManTable.addContainerListener(new ContainerListener() {

			@Override
			public void componentRemoved(ContainerEvent arg0) {

				savetoList(ManTable.getModel(), ManList);
			}

			@Override
			public void componentAdded(ContainerEvent e) {
				// TODO Auto-generated method stub

			}

		});

		JScrollPane ManscrollPane = new JScrollPane(ManTable);
		ManscrollPane.setBounds(10, 10, 250, 130);
		panel2.add(ManscrollPane);

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
				
				for(Rule a:AutoList) {
					int r = (int) (Math.random()*1000-500);
					double rr = (double) (r*0.01);

					a.setValue(rr);
				}

				fillJtabel();
			}
		});
		panel3.add(GerarAuto);

		JButton GravarAuto = new JButton("Gravar");
		GravarAuto.setBounds(300, 72, 89, 23);
		
		GravarAuto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataManagement.Save(AutoList);
				
			}
		});
		panel3.add(GravarAuto);

		JButton AvaliarAuto = new JButton("Avaliar");
		AvaliarAuto.setBounds(300, 117, 89, 23);
		AvaliarAuto.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				DataManagement.avaliar(spamcheck.isSelected(), hamcheck.isSelected(), AutoFN, AutoFP, AutoList);

			}
		});
		panel3.add(AvaliarAuto);

		//headers for the table
		String[] columns = new String[] {"Nome","Peso"};

		//create table with data
		DefaultTableModel AutoModel = new DefaultTableModel(null, columns);


		AutoTable = new JTable(AutoModel);

		AutoTable.setEnabled(false);

		JScrollPane AutoscrollPane = new JScrollPane(AutoTable);
		AutoscrollPane.setBounds(10, 10, 250, 130);

		panel3.add(AutoscrollPane);

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

	private void fillJtabel() {		

		//headers for the table
		String[] columns = new String[] {"Nome", "Peso"};

		//actual data for the table in a 2d array
		Object[][] Autodata = new Object[AutoList.size()][2];
		Object[][] Mandata = new Object[ManList.size()][2];

		for(int i =0; i<ManList.size(); i++) {
			Mandata[i][0] = ManList.get(i).getName();
			Mandata[i][1] = ManList.get(i).getValue();
			//System.out.println(ManList.get(i).getValue());
		}

		for(int i =0; i<AutoList.size(); i++) {
			Autodata[i][0] = AutoList.get(i).getName();
			Autodata[i][1] = AutoList.get(i).getValue();
		}

		//create table with data
		DefaultTableModel AutoModel = new DefaultTableModel(Autodata, columns);
		DefaultTableModel ManModel = new DefaultTableModel(Mandata, columns);

		AutoTable.setModel(AutoModel);
		ManTable.setModel(ManModel);
	}

	public void savetoList(TableModel table, List<Rule> list) {

		list.clear();

		for(int row = 0; row < table.getRowCount(); row++) {

			String s = (String) table.getValueAt(row,0);
			String b = (String) String.valueOf(table.getValueAt(row,1));

			Rule a = new Rule(s,Double.parseDouble(b));
			list.add(a); 


		}
	}


}