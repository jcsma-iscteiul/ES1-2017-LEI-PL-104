
import java.io.File;
import org.junit.Test;
import Readers.LogReader;
import Readers.ReadConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;

public class AntiSpamFilterProblemTest {

	@Test
	public final void test() {
		ReadConfiguration rc = new ReadConfiguration(new File("resources\\rules.cf").getAbsolutePath());
		LogReader ls = new LogReader(new File("resources\\spam.log").getAbsolutePath());
		LogReader lh = new LogReader(new File("resources\\ham.log").getAbsolutePath());
		new AntiSpamFilterProblem(rc, ls, lh);
		
		
	}
}
