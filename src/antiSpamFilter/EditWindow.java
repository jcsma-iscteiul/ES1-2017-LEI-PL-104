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

	private ReadConfiguration rc;

	/***
	 * This constructor builds a new Edit Window. This window will allow you to change things in the file rules.cf.
	 * You can change the weights of the rules that you want. 
	 * 
	 * @author rccms-iscteiul
	 */
	public EditWindow(ReadConfiguration rc) {
		this.rc = rc;
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

		String[] weights = {"-5","-4","-3","-2","-1","0","1","2","3","4","5"};
		rulesComboBox.setModel(new DefaultComboBoxModel<>(rc.getConfiguration().keySet().toArray()));
		weightsComboBox.setModel(new DefaultComboBoxModel<>(weights));
		weightsComboBox.setSelectedItem(rc.getConfiguration().get(rulesComboBox.getSelectedItem()));


		rulesComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				weightsComboBox.setSelectedItem(rc.getConfiguration().get(rulesComboBox.getSelectedItem()));

			}
		});


		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				rc.getConfiguration().put(rulesComboBox.getSelectedItem().toString(), weightsComboBox.getSelectedItem().toString());
				rc.writeConfig();
			}
		});

		f.setVisible(true);
	}

}
