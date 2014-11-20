
import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

	public class TestExec{
		Map<String, String> expected;
		Map<String, String> result;	

	@Before
	public  void setUpBeforeClass() throws Exception {
		String args[]={"--cmd=send_message","--user_id=4897","--message=Hello"};
		
		
		this.result=Exec.parsingArgs(args);
		 
		this.expected = new LinkedHashMap<>();
		
		expected.put("--cmd=", "send_message");
		expected.put("--user_id=", "4897");
		expected.put("--message=", "Hello");
		
		
	}

	@Test
	public void test() {
		assertEquals(expected, result);
	}


	}


