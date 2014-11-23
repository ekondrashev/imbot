import java.util.LinkedHashMap;
import java.util.Map;
import junit.framework.TestCase;

import org.junit.Test;


public class TestMyExec extends TestCase{

	@Test
	public final void testDecodeToMap() {
		
		String args[]={"--cmd=sendmessage","-userid=vasia","-pass=123456"}; //My parameters 
		
		Map<String,String> myHashFromExec=Parsing.decodeToMapString(args);

		Map<String,String> myExample= new LinkedHashMap<>();

		myExample.put("command","sendmessage");
		myExample.put("userid","vasia");
		myExample.put("pass","123456");
		
		
		assertEquals(myHashFromExec, myExample);
		
	}
	
	@Test
	public final void testControlHelp() {
		
		String args[]={"--help"}; //My parameters 
		String USAGE = "Usage: Exec --cmd=<os_cmd> -param1=<value> -param2=<value>";
		
		Map<String,String> myHashFromExec=Parsing.decodeToMapString(args);

		Map<String,String> myExample= new LinkedHashMap<>();

		myExample.put("help",USAGE);
		
		assertEquals(myHashFromExec, myExample);
		
	}

	
	@Test
	public final void testControlPattern() {
		String args[]={"--cmd=sendmessage","-userid=vasia","-pass=123456",}; //My parameters 
		
		Map<String,String> myHashFromExec=Parsing.decodeToMapPattern(args);
		
		Map<String,String> myExample= new LinkedHashMap<>();
		myExample.put("command","sendmessage");
		myExample.put("userid","vasia");
		myExample.put("pass","123456");
		
		assertEquals(myHashFromExec, myExample);
		
	}

}
