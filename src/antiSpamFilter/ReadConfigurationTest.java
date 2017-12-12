package antiSpamFilter;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

public class ReadConfigurationTest {

	ReadConfiguration rc = new ReadConfiguration("resources/rules.cf");

	@Test
	public final void testReadConfiguration() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetConfiguration() {
		HashMap<String,String> map = new HashMap<String,String>();
//		Scanner s = new Scanner("resources/rules.cf");
//		while(s.hasNextLine()) {
//			String l = s.nextLine();
//			if(l.split(" ").length != 1) {
//				map.put(l.split(" ")[0], l.split(" ")[1]);
//			}else {
//				map.put(l, "");
//			}
//		}
		assertEquals("Configuration not equals",rc.getConfiguration(),map);
	}

	@Test
	public final void testApplyRandomConfig(){
		HashMap<String, String> map = rc.getConfiguration();
		rc.applyRandomConfig();
		assertNotEquals("Not Changed", map, rc.getConfiguration());
	}

	@Test
	public final void testWriteConfig() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetWasTherePreviousConfig() {
		assertEquals("False", rc.getWasTherePreviousConfig(), true);
	}

}
