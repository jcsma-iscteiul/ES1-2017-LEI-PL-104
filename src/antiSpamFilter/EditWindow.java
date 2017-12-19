package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditWindow {

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel rulesAndWeightsPanel;
	private JPanel buttonsPanel;
	private JComboBox<Object> rulesComboBox;
	private JComboBox<Object> weightsComboBox;
	private JButton applyButton;	
	private JButton showAmountFPN;
	private ReadConfiguration rc;
	private ConfigurationWindow cw;

	/***
	 * This constructor builds a new Edit Window. This window will allow you to change things in the file rules.cf.
	 * You can change the weights of the rules that you want. 
	 * 
	 * @author rccms-iscteiul
	 */
	public EditWindow(ReadConfiguration rc, ConfigurationWindow cw) {
		this.rc = rc;
		this.cw = cw;
		build();
	}


	/***
	 * This method builds a frame with 2 JComboBox (one for the rules and one for the weights) and one Apply button. 
	 */
	private void build() {

		frame = new JFrame("Change rules.cf");
		Gui.getInstance().center(frame);
		frame.setSize(380, 80);

		//buttons
		applyButton = new JButton("Apply");
		showAmountFPN = new JButton("Show FP and FP for this new configuration");

		//comboBoxes
		rulesComboBox = new JComboBox<>();
		weightsComboBox = new JComboBox<>();

		//panels
		mainPanel = new JPanel(new GridLayout(2, 1));
		rulesAndWeightsPanel = new JPanel(new BorderLayout());
		buttonsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(rulesAndWeightsPanel);
		mainPanel.add(buttonsPanel);
		rulesAndWeightsPanel.add(rulesComboBox,BorderLayout.CENTER);
		rulesAndWeightsPanel.add(weightsComboBox,BorderLayout.EAST);
		buttonsPanel.add(applyButton);
		buttonsPanel.add(showAmountFPN);

		frame.add(mainPanel);


		//Configuration of ComboBoxes
		String[] weights = {"-5","-4","-3","-2","-1","0","1","2","3","4","5"};
		rulesComboBox.setModel(new DefaultComboBoxModel<>(rc.getConfiguration().keySet().toArray()));
		weightsComboBox.setModel(new DefaultComboBoxModel<>(weights));
		weightsComboBox.setSelectedItem(rc.getConfiguration().get(rulesComboBox.getSelectedItem()));

		
		
		//Action Listeners	
		rulesComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				weightsComboBox.setSelectedItem(rc.getConfiguration().get(rulesComboBox.getSelectedItem()));

			}
		});


		showAmountFPN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cw.showFPN();

			}
		});


		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				rc.getConfiguration().put(rulesComboBox.getSelectedItem().toString(), weightsComboBox.getSelectedItem().toString());
				rc.writeConfig();
				cw.calculateFPN();

			}
		});

		frame.setVisible(true);
	}

}
