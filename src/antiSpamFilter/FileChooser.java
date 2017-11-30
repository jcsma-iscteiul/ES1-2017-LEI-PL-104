package antiSpamFilter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	private String a;
	private String b;
	private String cf;
	
	/***
	 * This class is used to build a JFileChooser with filter for files. 
	 * It returns the path to the selected file.
	 * 
	 * @author rccms-iscteiul
	 * 
	 * @param a - file description (ex: *.txt)
	 * @param b - file extension (ex: txt)
	 * 
	 */
	
	public FileChooser(String a, String b) {
		this.a = a;
		this.b=b;
	}
	
	
	/***
	 * Builds a JFileChooser.
	 * 
	 * @return path to the file with the description and extension specified in constructor
	 * 
	 */
	public String buildFileChooser() {
		
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter(a, b));
		fc.showOpenDialog(null);
		cf = fc.getSelectedFile().getAbsolutePath();
		return cf;
		
	}

}
