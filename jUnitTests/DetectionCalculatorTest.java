
import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;
import Readers.LogReader;
import Readers.ReadConfiguration;
import Utils.DetectionCalculator;

public class DetectionCalculatorTest {

	@Test
	public final void test() {
		ReadConfiguration rc = new ReadConfiguration(new File("resources\\rules.cf").getAbsolutePath());
		LogReader lrSPAM = new LogReader(new File("resources\\spam.log").getAbsolutePath());
		LogReader lrHAM = new LogReader(new File("resources\\ham.log").getAbsolutePath());
		DetectionCalculator dc = new DetectionCalculator(rc, lrSPAM, lrHAM);
		assertTrue(dc.calculateFN() >= 0);
		assertTrue(dc.calculateFP() >= 0);

	}

}
