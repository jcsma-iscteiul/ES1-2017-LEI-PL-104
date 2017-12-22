package Tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import org.junit.Test;
import GUI.ConfigurationWindow;
import Readers.AntiSpamFilesReader;
import Readers.LogReader;
import Readers.ReadConfiguration;
import Utils.DetectionCalculator;
import Utils.OptimalCalculator;

public class ConfigurationWindowTest {

	@Test
	public void test() {
	
		
		
		ReadConfiguration rc = new ReadConfiguration(new File("resources\\rules.cf").getAbsolutePath());
		AntiSpamFilesReader asfr = new AntiSpamFilesReader(
				new File("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rf").getAbsolutePath(),
				new File("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rs").getAbsolutePath());

		OptimalCalculator oc = new OptimalCalculator(rc,asfr);
	
	
		ConfigurationWindow cw = new ConfigurationWindow();

	
		
		
	}

}
