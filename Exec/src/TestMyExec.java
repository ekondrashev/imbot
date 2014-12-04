import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;


public class TestMyExec extends TestCase {

	@Test
	public final void testDecodeToMap() {
		
		String args[]={"--cmd=sendmessage","-userid=vasia","-pass=123456"}; //My parameters 
		 			
		
		Exec exec=new Exec(); 
		
		Map<String,String> myHashFromExec=exec.decodeToMap(args);

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
		
		Exec exec=new Exec(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);

		Map<String,String> myExample= new LinkedHashMap<>();

		myExample.put("help",USAGE);
		
		assertEquals(myHashFromExec, myExample);
		
	}

	
	@Test
	public final void testControlPattern() {
		String args[]={"--cmd=sendmessage","-userid=vasia","-pass=123456",}; //My parameters 
		
		Exec2 exec=new Exec2(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);
		Map<String,String> myExample= new LinkedHashMap<>();
		myExample.put("command","sendmessage");
		myExample.put("userid","vasia");
		myExample.put("pass","123456");
		
		assertEquals(myHashFromExec, myExample);
		
	}
	
	@Test
	public final void testControlStart() {
		String args[]={"--cmd=start"}; //My parameters 
		
		Exec2 exec=new Exec2(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);
		Map<String,String> myExample= new LinkedHashMap<>();
		myExample.put("command","start");
		
		assertEquals(myHashFromExec, myExample);
		
	}
	
	@Test
	public final void testControlStop() {
		String args[]={"--cmd=stop"}; //My parameters 
		
		Exec2 exec=new Exec2(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);
		Map<String,String> myExample= new LinkedHashMap<>();
		myExample.put("command","stop");
		
		assertEquals(myHashFromExec, myExample);
		
	}

}

