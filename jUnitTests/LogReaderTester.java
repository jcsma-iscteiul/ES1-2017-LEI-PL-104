
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import Readers.LogReader;

public class LogReaderTester {

	@Test
	public final void testGetMsgRules() {
		final LogReader lr = new LogReader("resources/spam.log");
		HashMap<String, String[]> map = lr.getMsgRules();
		String[] test1 = {"teste"};
		lr.addContent("test", test1);
		map.put("test", test1);
		
		assertEquals("Fields don't match", lr.getMsgRules(), map);
		
	}
	

}
