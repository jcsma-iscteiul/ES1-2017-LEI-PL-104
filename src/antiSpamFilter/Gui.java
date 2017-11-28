package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Gui {
	
	private JFrame frame;
	private JPanel cfPanel;
	private JPanel log1Panel;
	private JPanel log2Panel;
	private JTextField cfPath;
	private JButton cfButton;
	private JTextField log1Path;
	private JButton log1Button;
	private JTextField log2Path;
	private JButton log2Button;
	private JButton next;
	
	public Gui () {
		pathWindow();
	}
	
	
	private void pathWindow() {
		frame = new JFrame("Anti-spam Filtering Configuration");
		frame.setLayout(new GridLayout(4, 1));
	
		frame.setSize(400,125);
		center(frame);
		
		cfPanel = new JPanel(new BorderLayout());
		log1Panel = new JPanel(new BorderLayout());
		log2Panel = new JPanel(new BorderLayout());
		frame.add(cfPanel);
		frame.add(log1Panel);
		frame.add(log2Panel);
		
		cfPath = new JTextField();
		cfButton = new JButton("CF");
		cfPanel.add(cfPath,BorderLayout.CENTER);
		cfPanel.add(cfButton,BorderLayout.LINE_END);
		
		log1Path = new JTextField();
		log1Button = new JButton("log1");
		log1Panel.add(log1Path,BorderLayout.CENTER);
		log1Panel.add(log1Button,BorderLayout.LINE_END);
		
		log2Path = new JTextField();
		log2Button = new JButton("log2");
		log2Panel.add(log2Path,BorderLayout.CENTER);
		log2Panel.add(log2Button,BorderLayout.LINE_END);
		
		
		next = new JButton("Next >>");
		frame.add(next);
		
		cfButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				
				
				fc.setFileFilter(new FileNameExtensionFilter("*.cf", "cf"));
				fc.showOpenDialog(null);
				
				String cf = fc.getSelectedFile().getAbsolutePath();
				cfPath.setText(cf);
				
				
				
			}
		});
		
		log1Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = new LogFileChooser().buildFileChooser();
				log1Path.setText(path);
			}
		});
		
		log2Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = new LogFileChooser().buildFileChooser();
				log2Path.setText(path);
			}
		});
		
		
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				boolean a = cfPath.getText().isEmpty();
//				boolean b = log1Path.getText().isEmpty();
//				boolean c = log2Path.getText().isEmpty();
//				if(!a && !b && !c) {
				new ConfigurationWindow();
//				frame.setVisible(false);
//				
//				}
//				else {
//					JFrame popUP = new JFrame("Warning!");
//					popUP.setSize(300, 100);
//					center(popUP);
//				    JLabel label = new JLabel("Warning!");
//					popUP.setLayout(new BorderLayout());
//					popUP.add(label,BorderLayout.CENTER);
//					popUP.setVisible(true);
//				}
				
			}
		});
		
		
		
		frame.setVisible(true);
	}
	
	
	private void center(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public static void main(String[] args) {
		new Gui();
	}
	
}
