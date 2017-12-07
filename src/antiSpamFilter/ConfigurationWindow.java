package antiSpamFilter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConfigurationWindow {

	private JFrame window;
	private JButton GRC;
	private JButton EDIT;
	private JButton FPN;
	private ReadConfiguration rc;
	private LogReader lr1;
	private LogReader lr2;


	/***
	 * Builds a configuration window.
	 * 
	 * @author rccms-iscteiul
	 */

	public ConfigurationWindow() {
		buildWindow();
	}

	/***
	 * Builds a window with three buttons. Choose between Generate Random Configuration, Edit the file rules.cf or
	 * calculate FP and FN.
	 */
	private void buildWindow(){

		window = new JFrame("Configuration Window");
		window.setSize(400, 125);
		Gui.getInstance().center(window);
		window.setLayout(new GridLayout(3, 1));
		GRC = new JButton("Generate Random Configuration");
		EDIT = new JButton("Edit rules.cf");
		FPN = new JButton("Calculate FP e FN");
		window.add(GRC);
		window.add(EDIT);
		window.add(FPN);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String rulesPath = Gui.getInstance().getRulesPath();
		String spamPath = Gui.getInstance().getSpamPath();
		String hamPath = Gui.getInstance().getHamPath();
		lr1 = new LogReader(spamPath);
		lr2 = new LogReader(hamPath);
		rc = new ReadConfiguration(rulesPath);

		// Generate Random Configuration Button
		//This is what happens when you press the GRC Button
		GRC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				rc.applyRandomConfig();
				JOptionPane.showMessageDialog(null, "Done!");
			}
		});

		//Edit the file rules.cf
		//Here you can choose the rule that you want and change the weight of that rule
		//This button will open a new window
		EDIT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rc.getWasTherePreviousConfig()) {
					new EditWindow(rc);
				}else {
					JOptionPane.showMessageDialog(null, "Generate something first");
				}

			}
		});



		FPN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rc.getWasTherePreviousConfig()) {
					DetectionCalculator dc = new DetectionCalculator(rc, lr1, lr2);
					int FP = dc.calculateFP();
					int FN = dc.calculateFN();
					JOptionPane.showMessageDialog(null, "False positives found: "+FP+", False negatives found: "+FN);
				}else {
					JOptionPane.showMessageDialog(null, "Generate something first");
				}
			}
		});

	}

}
