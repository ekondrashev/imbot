
import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

	public class TestExec{
		Map<String, String> result;
		LinkedHashMap<Object, Object> expected;
	
	@Test
	public void test() {
		String args[]={"--cmd=send_message","--user_id=4897","--message=Hello"};
		result = Exec.parsingArgs(args);
		expected = new LinkedHashMap<>();
		
		expected.put("cmd", "send_message");
		expected.put("user_id", "4897");
		expected.put("message", "Hello");
		
		assertEquals(this.expected, this.result);
	}
   
	@Test
	public void test0() throws InterruptedException {
		String args[]={"--user_id=4897","--message=Hello"};
		this.result = Exec.parsingArgs(args);
		this.expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(expected, result);
	}
	
	@Test
	public void test1() throws InterruptedException {
		String args[]={"--help=send_message","--user_id=4897","--message=Hello"};
		this.result = Exec.parsingArgs(args);
		this.expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(expected, result);
	}
	
	@Test
	public void test2() throws InterruptedException {
		String args[]={"--help"};
		this.result = Exec.parsingArgs(args);
		this.expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(expected, result);
	}
	
	@Test
	public void test3() {
		String args[]={};
		this.result = Exec.parsingArgs(args);
		this.expected = new LinkedHashMap<>();
		expected.put("HELP", Exec.USAGE);
		assertEquals(expected, result);
	}
	
	
}


