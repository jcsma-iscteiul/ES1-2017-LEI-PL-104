package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Readers.ReadConfiguration;

public class EditWindow {

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel rulesAndWeightsPanel;
	private JPanel buttonsPanel;
	private JComboBox<Object> rulesComboBox;
	private JTextField rulesWeights;
	private JButton applyButton;	
	private JButton showAmountFPN;
	private ReadConfiguration rc;
	private ConfigurationWindow cw;

	/***
	 * This class builds a new Edit Window. This window will allow you to change things in the file rules.cf.
	 * You can change the weights of the rules that you want. 
	 * 
	 * @param rc ReadConfiguration
	 * @param cw ConfigurationWindow
	 * 
	 */
	public EditWindow(ReadConfiguration rc, ConfigurationWindow cw) {
		this.rc = rc;
		this.cw = cw;
		build();
	}

	/***
	 * This method builds a frame with one JComboBox (for the rules) and one JTextField (for the weights),
	 * one Apply Button that writes the new weight in the file rules.cf and one button to show False Positives and False Negatives
	 *  
	 */
	private void build() {

		frame = new JFrame("Change rules.cf");
		Gui.getInstance().center(frame);
		frame.setSize(380, 80);
		frame.setResizable(false);

		//buttons
		applyButton = new JButton("Apply");
		showAmountFPN = new JButton("Show FP and FP");

		//comboBox and JTextField
		rulesComboBox = new JComboBox<>();
		rulesWeights = new JTextField();

		//panels
		mainPanel = new JPanel(new GridLayout(2, 1));
		rulesAndWeightsPanel = new JPanel(new GridLayout(1, 2));
		buttonsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(rulesAndWeightsPanel);
		mainPanel.add(buttonsPanel);
		rulesAndWeightsPanel.add(rulesComboBox);
		rulesAndWeightsPanel.add(rulesWeights);
		buttonsPanel.add(applyButton);
		buttonsPanel.add(showAmountFPN);

		frame.add(mainPanel);


		//Configuration of ComboBox and JTextField
		rulesComboBox.setModel(new DefaultComboBoxModel<>(rc.getConfiguration().keySet().toArray()));
		rulesWeights.setText(rc.getConfiguration().get(rulesComboBox.getSelectedItem()));



		//Action Listeners	

		rulesComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				rulesWeights.setText(rc.getConfiguration().get(rulesComboBox.getSelectedItem()));

			}
		});

		//Show False Positives and False Negatives
		showAmountFPN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cw.showFPN();
			}
		});

		//Apply the new configuration
		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String peso = "";
				if(Double.parseDouble(rulesWeights.getText().toString())<=5 && Double.parseDouble(rulesWeights.getText().toString())>=-5) {
					peso = rulesWeights.getText().toString();
					System.out.println("PESO: "+peso);
					rc.getConfiguration().put(rulesComboBox.getSelectedItem().toString(), peso);
					rc.writeConfig();
					cw.calculateFPN();
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid weight, insert between -5 and 5!");
				}


			}
		});

		frame.setVisible(true);
	}

}
