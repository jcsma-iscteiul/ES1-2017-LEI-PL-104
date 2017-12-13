package antiSpamFilter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DetectionCalculatorTest {

	@Test
	public final void test() {
		ReadConfiguration rc = new ReadConfiguration("C:\\Users\\HP\\git\\ES1-2017-LEI-PL-104\\resources\\rules.cf");
		LogReader lrSPAM = new LogReader("C:\\Users\\HP\\git\\ES1-2017-LEI-PL-104\\resources\\spam.log");
		LogReader lrHAM = new LogReader("C:\\Users\\HP\\git\\ES1-2017-LEI-PL-104\\resources\\ham.log");
		DetectionCalculator dc = new DetectionCalculator(rc, lrSPAM, lrHAM);
		assertTrue(dc.calculateFN() >= 0);
		assertTrue(dc.calculateFP() >= 0);
		
	}

}
