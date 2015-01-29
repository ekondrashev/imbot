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

		Map<String, String> actual = Sneight.decodeToMap(args);

		assertEquals(actual, expected);
	}

	@Test
	public void testStopCmdLineOption() {
		String[] args = new String[] { "--cmd=stop" };
		Map<String, String> expected = new HashMap<>();
		expected.put("cmd", "stop");

		Map<String, String> actual = Sneight.decodeToMap(args);

		assertEquals(actual, expected);
	}
	@Test
	public void testSentMessageCmdLineOption() {
		String[] args = new String[] { "--cmd=send_message", "--user_id=12345", "--message=text" };
		Map<String, String> expected = new HashMap<>();
		expected.put("cmd", "send_message");
		expected.put("user_id", "12345");
		expected.put("message", "text");
		

		Map<String, String> actual = Sneight.decodeToMap(args);

		assertEquals(actual, expected);
	}

}
