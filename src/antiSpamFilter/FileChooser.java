package antiSpamFilter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	
	
	/***
	 * This class is used to build a JFileChooser with filter for files. 
	 * It returns the path to the selected file.
	 * @return
	 */
	
	public String buildFileChooser(String a, String b) {
		
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter(a, b));
		fc.showOpenDialog(null);
		String cf = fc.getSelectedFile().getAbsolutePath();
		return cf;
		
	}
	
	
	
	
	
	

}
