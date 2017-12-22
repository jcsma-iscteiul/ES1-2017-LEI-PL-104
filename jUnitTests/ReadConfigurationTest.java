
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import Readers.ReadConfiguration;

public class ReadConfigurationTest {

	ReadConfiguration rc = new ReadConfiguration("resources/rules.cf");

	@Test
	public final void testGetConfiguration() {
		HashMap<String,String> map = new HashMap<String,String>();
		assertNotEquals("Configuration not equals",rc.getConfiguration(),map);
	}

	@Test
	public final void testApplyRandomConfig(){
		HashMap<String, String> map = rc.getConfiguration();
		rc.applyRandomConfig();
		assertEquals("Not Changed", map, rc.getConfiguration());
	}


	@Test
	public final void testGetWasTherePreviousConfig() {
		assertEquals("False", rc.getWasTherePreviousConfig(), true);
	}

}
