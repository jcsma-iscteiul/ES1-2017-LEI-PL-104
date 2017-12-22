package Tests;

import java.io.File;
import org.junit.Test;
import Readers.AntiSpamFilesReader;
import Readers.ReadConfiguration;
import Utils.OptimalCalculator;

public class ConfigurationWindowTest {

	@Test
	public void test() {
	
		
		
		ReadConfiguration rc = new ReadConfiguration(new File("resources\\rules.cf").getAbsolutePath());
		AntiSpamFilesReader asfr = new AntiSpamFilesReader(
				new File("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rf").getAbsolutePath(),
				new File("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rs").getAbsolutePath());

		new OptimalCalculator(rc,asfr);
		
		
	}

}
