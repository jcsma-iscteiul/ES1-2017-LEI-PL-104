package antiSpamFilter;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConfigurationWindow {

	private JFrame window;
	private JButton GRC;
	private JButton EDIT;


	/***
	 * Builds a configuration window with two buttons to choose between Generate Random Configuration and Edit the file rules.cf.
	 * @author rccms-iscteiul
	 */
	
	public ConfigurationWindow() {
		buildWindow();
	}


	private void buildWindow(){

		window = new JFrame("Configuration Window");
		window.setSize(400, 125);
		center(window);
		window.setLayout(new GridLayout(2, 1));
		GRC = new JButton("Generate Random Configuration");
		EDIT = new JButton("Edit rules.cf");
		window.add(GRC);
		window.add(EDIT);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		GRC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String rulesPath = Gui.getInstance().getRulesPath();
				String spamPath = Gui.getInstance().getSpamPath();
				String hamPath = Gui.getInstance().getHamPath();
				RandomConfig rc = new RandomConfig(rulesPath);
				LogReader lr1 = new LogReader(spamPath);
				LogReader lr2 = new LogReader(hamPath);
				DetectionCalculator dc = new DetectionCalculator(rc, lr1, lr2);
				int FP = dc.calculateFP();
				int FN = dc.calculateFN();
				JOptionPane.showMessageDialog(null, "False positives found: "+FP+", False negatives found: "+FN);
			}
		});

		EDIT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame f = new JFrame();
				center(f);
				f.setSize(300, 60);
				JComboBox comboBox = new JComboBox<>();
				ArrayList<String> list;

				File file = new File(Gui.getInstance().getRulesPath());

				try{
					Scanner s = new Scanner(file);
					list = new ArrayList<String>();
					while (s.hasNextLine()){
						String l = s.nextLine();
						list.add(l.split(" ")[0]);
					}
					s.close();

					comboBox.setModel(new DefaultComboBoxModel<>(list.toArray()));
					comboBox.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							
						}
					});

				}
				catch (Exception exc) {

				}

				f.add(comboBox);
				f.setVisible(true);;
			}
		});

	}


	private void center(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

}
