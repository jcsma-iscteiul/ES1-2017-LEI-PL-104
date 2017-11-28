package antiSpamFilter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LogFileChooser {
	
	
	public String buildFileChooser() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
		fc.showOpenDialog(null);
		String cf = fc.getSelectedFile().getAbsolutePath();
		return cf;
		
	}
	
	
	
	
	
	

}
