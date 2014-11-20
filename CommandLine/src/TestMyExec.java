import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;


public class TestMyExec extends TestCase{


	@Test
	public final void testDecodeToMap() {
		
		String args[]={"--cmd=sendmessage","-userid=vasia","-pass=123456"}; //My parameters 
		
		Map<String,String> myHashFromExec=Exec.decodeToMap(args);

		Map<String,String> myExample= new LinkedHashMap<>();

		myExample.put("command","sendmessage");
		myExample.put("userid","vasia");
		myExample.put("pass","123456");
		
		
		assertEquals(myHashFromExec, myExample);
		
	}

}
