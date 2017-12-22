package Tests;

import java.io.File;
import org.junit.Test;
import GUI.ConfigurationWindow;
import GUI.EditWindow;
import Readers.ReadConfiguration;

public class EditWindowTest {

	@Test
	public void test() {
		
		
		ReadConfiguration rc = new ReadConfiguration(new File("resources\\rules.cf").getAbsolutePath());
		ConfigurationWindow cw = new ConfigurationWindow();
		
		
		EditWindow editWindow = new EditWindow(rc, cw);
		
	}

	
	
}
