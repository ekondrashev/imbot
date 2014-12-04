import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import junit.framework.TestCase;

public class TestParsing extends TestCase {

	@Test
	public void testStartCmdLineOption() {
		String[] args = new String[] { "--cmd=start" };
		Map<String, String> expected = new HashMap<>();
		expected.put("cmd", "start");

		Map<String, String> actual = Sneight.DecodeToMap(args);

		assertEquals(actual, expected);
	}

	@Test
	public void testStopCmdLineOption() {
		String[] args = new String[] { "--cmd=stop" };
		Map<String, String> expected = new HashMap<>();
		expected.put("cmd", "stop");

		Map<String, String> actual = Sneight.DecodeToMap(args);

		assertEquals(actual, expected);
	}

}
