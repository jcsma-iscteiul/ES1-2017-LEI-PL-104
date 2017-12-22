package Tests;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;
import Readers.AntiSpamFilesReader;
import Readers.ReadConfiguration;
import Utils.OptimalCalculator;

public class OptimalCalculatorTest {

	@Test
	public void test() {
		ReadConfiguration rc = new ReadConfiguration(new File("resources\\rules.cf").getAbsolutePath());
		AntiSpamFilesReader asfr = new AntiSpamFilesReader(
				new File("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rf").getAbsolutePath(),
				new File("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rs").getAbsolutePath());

		OptimalCalculator oc = new OptimalCalculator(rc,asfr);


		assertTrue(oc.calculateBestFset() >= 0);
		assertTrue(oc.getFN() >= 0);
		assertTrue(oc.getFP() >= 0);
	}


}
