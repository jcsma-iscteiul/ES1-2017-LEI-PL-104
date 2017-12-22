package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Readers.AntiSpamFilesReader;
import Readers.LogReader;
import Readers.ReadConfiguration;
import Utils.DetectionCalculator;
import Utils.OptimalCalculator;
import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;

public class ConfigurationWindow {

	private JFrame window;
	private JButton GRC;
	private JButton EDIT;
	private JButton FPN;
	private JButton GOC;
	private ReadConfiguration rc;
	private LogReader lr1;
	private LogReader lr2;
	private int FP;
	private int FN;
	private ConfigurationWindow cw;


	/***
	 * Builds a configuration window.
	 * 
	 * @author rccms-iscteiul
	 */

	public ConfigurationWindow() {
		cw = this;
		buildWindow();
		calculateFPN();
	}

	/***
	 * Builds a window where you can choose between:
	 * generate a random configuration,
	 * edit/change that configuration,
	 * calculate the amount of FP and FN,
	 * generate an optimal configuration using the NSGA-II algorithm 
	 * 
	 */
	private void buildWindow(){

		window = new JFrame("Configuration Window");
		window.setSize(400, 125);
		Gui.getInstance().center(window);
		window.setLayout(new GridLayout(4, 1));
		GRC = new JButton("Generate Random Configuration");
		EDIT = new JButton("Edit rules.cf");
		FPN = new JButton("Calculate FP e FN");
		GOC = new JButton("Generate Optimal Configuration");
		window.add(GRC);
		window.add(EDIT);
		window.add(FPN);
		window.add(GOC);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String rulesPath = Gui.getInstance().getRulesPath();
		String spamPath = Gui.getInstance().getSpamPath();
		String hamPath = Gui.getInstance().getHamPath();
		lr1 = new LogReader(spamPath);
		lr2 = new LogReader(hamPath);
		rc = new ReadConfiguration(rulesPath);

		
		
		
		// Action Listeners
		
		// Generate Random Configuration Button
		//This is what happens when you press the GRC Button
		GRC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				rc.applyRandomConfig();
				calculateFPN();
				JOptionPane.showMessageDialog(null, "Done!\n"+"False Positives: "+FP+"\nFalse Negatives: "+FN);
			}
		});

		
		//This button will open a new window
		//Edit the file rules.cf
		//Here you can choose the rule that you want and change the weight of that rule
		EDIT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(rc.getWasTherePreviousConfig()) {
					new EditWindow(rc, cw);
				}else {
					JOptionPane.showMessageDialog(null, "Generate something first");
				}

			}
		});

		//This button shows the amount of False Positives and False Negatives of the current configuration
		FPN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showFPN();
			}
		});
		
		
		GOC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AntiSpamFilterAutomaticConfiguration();
				File RF = new File("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rf");
				File RS = new File("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rs");
				AntiSpamFilesReader ASFR = new AntiSpamFilesReader(RF.getAbsolutePath(),RS.getAbsolutePath());
				OptimalCalculator o = new OptimalCalculator(rc,ASFR);
				calculateOFPN(o);
				showFPN();
				
			}
		});


	}

	
	public void showFPN() {
		if(rc.getWasTherePreviousConfig()) {
			JOptionPane.showMessageDialog(null, "False positives found: "+FP+", False negatives found: "+FN);
		}else {
			JOptionPane.showMessageDialog(null, "Generate something first");
		} 
		
	}
	
	public void calculateOFPN(OptimalCalculator oc) {
		FP = (int) oc.getFP();
		FN = (int) oc.getFN();
	}

	public void calculateFPN () {
		DetectionCalculator dc = new DetectionCalculator(rc, lr1, lr2);
		FP = dc.calculateFP();
		FN = dc.calculateFN();
	}
	

}

