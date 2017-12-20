package Tests;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import GUI.FileChooser;

public class FileChooserTest {

	@Test
	public final void test() {
		FileChooser fc = new FileChooser("rules.cf","cf");
		String l = fc.buildFileChooser();
		System.out.println(l);
		String[] filepath = l.split("");
		String test = filepath[filepath.length-2]+filepath[filepath.length-1];
		System.out.println(test);
		assertEquals("Must be the same","cf",test);
	}

}
