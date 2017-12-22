package GUI;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

	private String a;
	private String b;
	private String cf;
	JFileChooser fc = new JFileChooser();


	/***
	 * This class is used to build a JFileChooser with filter for files. 
	 * 
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

		fc.setFileFilter(new FileNameExtensionFilter(a, b));
		fc.setCurrentDirectory(new File("resources"));
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			cf = fc.getSelectedFile().getAbsolutePath();
		}else if(result == JFileChooser.CANCEL_OPTION) {
			cf = "cancel";
		}
		return cf;
	}



}
