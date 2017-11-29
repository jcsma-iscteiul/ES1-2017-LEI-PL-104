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


		GRC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String path = Gui.getInstance().getRulesPath();
				new RandomConfig(path);


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

				File file = new File("C:\\Users\\HP\\Desktop\\rules.cf");

				try{
					Scanner s = new Scanner(file);
					list = new ArrayList<String>();
					while (s.hasNext()){
						list.add(s.next());
					}
					s.close();

					comboBox.setModel(new DefaultComboBoxModel<>(list.toArray()));


					System.out.println(list);
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
