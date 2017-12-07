package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditWindow {

	private ArrayList<String> rulesList;
	private ArrayList<String> weightsList;
	private File file;

	/***
	 * This constructor builds a new Edit Window. This window will allow you to change things in the file rules.cf.
	 * You can change the weights of the rules that you want. 
	 * 
	 * @author rccms-iscteiul
	 */
	public EditWindow() {
		file = new File(Gui.getInstance().getRulesPath());
		build();
	}


	/***
	 * This method builds the frame with 2 JComboBox (one for the rules and one for the weights) and one Apply button. 
	 */
	private void build() {

		JFrame f = new JFrame("Change rules.cf");
		f.setLayout(new GridLayout(2,1));
		Gui.getInstance().center(f);
		f.setSize(380, 80);
		JPanel p = new JPanel(new BorderLayout());
		JComboBox<Object> rulesComboBox = new JComboBox<>();
		JComboBox<Object> weightsComboBox = new JComboBox<>();
		JButton applyButton = new JButton("Apply");
		p.add(rulesComboBox,BorderLayout.CENTER);
		p.add(weightsComboBox,BorderLayout.EAST);
		f.add(p);
		f.add(applyButton);

		takeCareOfRules();
		takeCareOfWeights();

		rulesComboBox.setModel(new DefaultComboBoxModel<>(rulesList.toArray()));
		weightsComboBox.setModel(new DefaultComboBoxModel<>(weightsList.toArray()));

		
		rulesComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String currentWeight = theCurrentWeightofRule(rulesComboBox.getSelectedItem().toString());
				weightsComboBox.setSelectedItem(currentWeight);
				
			}
		});
		
		
		applyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 // apply not yet implemented
				
			}
		});

		f.setVisible(true);
	}

	
	private String theCurrentWeightofRule(String a) {
		
		String weight = null;
		
		try {
			Scanner s = new Scanner(file);
			while (s.hasNextLine()){
				String line = s.nextLine();
				String rule = line.split(" ")[0];
				
				if(rule.equals(a)) {
					weight = line.split(" ")[1]; 
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return weight;
		
	}
	
	

	private void takeCareOfRules (){

		try {

			rulesList = new ArrayList<String>();

			Scanner s = new Scanner(file);
			while (s.hasNextLine()){
				String line = s.nextLine();
				String rule = line.split(" ")[0];
				rulesList.add(rule);
			}
			s.close();

		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}

	}


	private void takeCareOfWeights (){

		weightsList = new ArrayList<String>();

		for (int i = -5 ; i<6; i++) {
			weightsList.add(String.valueOf(i));
		}

	}


}
