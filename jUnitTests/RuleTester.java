package antiSpamFilter;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RuleTester {
	
	private Rule test = new Rule("Regra", -4);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		
	}
	
	@Test
	final void testGetRule() {
		assertEquals("Regra", test.getRule(), "O nome da regra de ser Regra");
	}

	@Test
	final void testGetWeight() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testEquals() {
		fail("Not yet implemented"); // TODO
	}

}
