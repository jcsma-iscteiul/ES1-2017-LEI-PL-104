
import javax.swing.JButton;

import org.junit.Test;
import GUI.ConfigurationWindow;
import GUI.EditWindow;
import GUI.Gui;
import Readers.ReadConfiguration;

public class PackageGuiTest {

	@Test
	public void test() {
		
		Gui g = Gui.getInstance();
		g.setPathsManuallyForTest("resources/ham.log", "resources/spam.log", "resources/rules.cf");
		ReadConfiguration rc = new ReadConfiguration(g.getRulesPath());
		ConfigurationWindow cw = new ConfigurationWindow();
		new EditWindow(rc, cw);
		JButton[] buttons = g.getButtonsForTesting();
		for(JButton button : buttons){
			button.doClick();
		}
		
	}
	
	
}
