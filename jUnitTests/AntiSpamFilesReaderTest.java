
import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import Readers.AntiSpamFilesReader;

public class AntiSpamFilesReaderTest {

	@Test
	public void test() {
		
		
		
		String fileRF = new File(("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rf")).getAbsolutePath();
		String fileRS = new File(("experimentBaseDirectory\\referenceFronts\\AntiSpamFilterProblem.rf")).getAbsolutePath();
		
		AntiSpamFilesReader reader = new AntiSpamFilesReader(fileRF, fileRS);
		
		
		ArrayList<String> lista = reader.getRuleSet();
		lista.add("um");
		
		ArrayList<Double> lista1 = reader.getFN();
		ArrayList<Double> lista2 = reader.getFP();
		lista1.add(1.1);
		lista2.add(2.2);
		
		assertTrue(reader.getRuleSet().contains("um"));
		assertTrue(reader.getFN().contains(1.1));
		assertTrue(reader.getFP().contains(2.2));
	}
		
}