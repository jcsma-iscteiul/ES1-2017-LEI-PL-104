package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Gui {

	private JFrame frame;
	private JPanel cfPanel;
	private JPanel log1Panel;
	private JPanel log2Panel;
	private JLabel label;
	private JTextField cfPath;
	private JButton cfButton;
	private JTextField log1Path;
	private JButton log1Button;
	private JTextField log2Path;
	private JButton log2Button;
	private JButton next;


	/***
	 * Builds a new GUI
	 */
	public Gui () {
		pathWindow();
	}

	/***
	 * This method builds a window to choose the path to the files.
	 */
	private void pathWindow() {
		frame = new JFrame("Anti-spam Filtering Configuration");
		frame.setLayout(new GridLayout(5, 1));
		frame.setSize(400,150);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		center(frame);


		label = new JLabel("Choose a path for each file.");
		cfPanel = new JPanel(new BorderLayout());
		log1Panel = new JPanel(new BorderLayout());
		log2Panel = new JPanel(new BorderLayout());
		next = new JButton("Next >>");

		cfPath = new JTextField();
		cfPath.setEditable(false);
		cfPath.setText("rules.cf");
		cfPath.setForeground(Color.GRAY);
		cfButton = new JButton("Choose...");
		cfPanel.add(cfPath,BorderLayout.CENTER);
		cfPanel.add(cfButton,BorderLayout.LINE_END);

		log1Path = new JTextField();
		log1Path.setEditable(false);
		log1Path.setText("spam.log");
		log1Path.setForeground(Color.GRAY);
		log1Button = new JButton("Choose...");
		log1Panel.add(log1Path,BorderLayout.CENTER);
		log1Panel.add(log1Button,BorderLayout.LINE_END);

		log2Path = new JTextField();
		log2Path.setText("ham.log");
		log2Path.setForeground(Color.GRAY);
		log2Path.setEditable(false);
		log2Button = new JButton("Choose...");
		log2Panel.add(log2Path,BorderLayout.CENTER);
		log2Panel.add(log2Button,BorderLayout.LINE_END);


		frame.add(label);
		frame.add(cfPanel);
		frame.add(log1Panel);
		frame.add(log2Panel);
		frame.add(next);

		cfButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String a = new FileChooser().buildFileChooser("*.cf", "cf");
				if(a.contains("rules.cf")) {
					cfPath.setForeground(Color.black);
					cfPath.setText(a);
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong file. Try again.");
				}
			}
		});

		log1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String a = new FileChooser().buildFileChooser("*.log", "log");
				if(a.contains("spam.log")) {
					log1Path.setForeground(Color.black);
					log1Path.setText(a);
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong file. Try again.");
				}
			}
		});

		log2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String a = new FileChooser().buildFileChooser("*.log", "log");
				if(a.contains("ham.log")) {
					log2Path.setForeground(Color.black);
					log2Path.setText(a);
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong file. Try again.");
				}
			}
		});


		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String a = "rules.cf";
				String b = "spam.log";
				String c = "ham.log";
				if(!cfPath.getText().equals(a) && !log1Path.getText().equals(b) && !log2Path.getText().equals(c)) {
					new ConfigurationWindow();
					frame.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please choose a path for each file");
				}

			}
		});


		frame.setVisible(true);
	}


	public void center(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	public static void main(String[] args) {
		new Gui();
	}

}
