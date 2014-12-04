import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class TestExec {
	Map<String, String> result;
	LinkedHashMap<Object, Object> expected;

	@Test
	public void test() {
		String args[] = { "--cmd=send_message", "--user_id=4897",
				"--message=Hello" };
		result = Exec.parsingArgs(args);
		expected = new LinkedHashMap<>();
		expected.put("cmd", "send_message");
		expected.put("user_id", "4897");
		expected.put("message", "hello");

		assertEquals(this.expected, this.result);
	}

	@Test
	public void test0() throws InterruptedException {
		String args[] = { "--user_id=4897", "--message=Hello" };
		result = Exec.parsingArgs(args);
		expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(expected, result);
	}

	@Test
	public void test1() throws InterruptedException {
		String args[] = { "--help=send_message", "--user_id=4897",
				"--message=Hello" };
		result = Exec.parsingArgs(args);
		expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(expected, result);
	}

	@Test
	public void test2() throws InterruptedException {
		String args[] = { "--help" };
		result = Exec.parsingArgs(args);
		expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(expected, result);
	}

	@Test
	public void test3() {
		String args[] = {};
		result = Exec.parsingArgs(args);
		expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(this.expected, this.result);
	}

	@Test
	public void test4() {
		String args[] = { "--cmd=" };
		this.result = Exec.parsingArgs(args);
		this.expected = new LinkedHashMap<>();
		this.expected.put("HELP", Exec.USAGE);
		assertEquals(this.expected, this.result);
	}

	@Test
	public void test5() {
		String args[] = { "--user_id=send_message", "--cmd=4897",
				"--message=Hello" };
		result = Exec.parsingArgs(args);
		expected = new LinkedHashMap<>();

		expected.put("user_id", "send_message");
		expected.put("cmd", "4897");
		expected.put("message", "hello");

		assertEquals(this.expected, this.result);
	}

	@Test
	public void test6() {
		String args[] = { "--CMD=send_message", "--user_id=4897",
				"--message=Hello" };
		result = Exec.parsingArgs(args);
		expected = new LinkedHashMap<>();

		expected.put("cmd", "send_message");
		expected.put("user_id", "4897");
		expected.put("message", "hello");

		assertEquals(this.expected, this.result);
	}

	@Test
	public void test7() {
		String args[] = { "--cmd=send_message", "--help=send_message",
				"--message=Hello" };
		this.result = Exec.parsingArgs(args);
		this.expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(expected, result);
	}
}
