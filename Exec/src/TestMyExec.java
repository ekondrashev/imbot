import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;


public class TestMyExec extends TestCase {

	@Test
	public final void testDecodeToMap() {
		
		String args[]={"--cmd=sendmessage","-userid=vasia","-pass=123456"}; //My parameters 
		 			
		
		ParsingString exec=new ParsingString(); 
		
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
		
		ParsingString exec=new ParsingString(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);

		Map<String,String> myExample= new LinkedHashMap<>();

		myExample.put("help",USAGE);
		
		assertEquals(myHashFromExec, myExample);
		
	}

	
	@Test
	public final void testControlPattern() {
		String args[]={"--cmd=sendmessage","-user_id=vasia","-pass_=123456",}; //My parameters 
		
		ParsingRegulary exec=new ParsingRegulary(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);
		Map<String,String> myExample= new LinkedHashMap<>();
		myExample.put("command","sendmessage");
		myExample.put("user_id","vasia");
		myExample.put("pass_","123456");
		
		assertEquals(myHashFromExec, myExample);
		
	}
	
	@Test
	public final void testControlPatternPrint() {
		String args[]={"--cmd=print","-userid=vasia","-sendmessage=hello",}; //My parameters 
		//--cmd=print -user_id=vasia -send_message=hello
		ParsingRegulary exec=new ParsingRegulary(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);
		Map<String,String> myExample= new LinkedHashMap<>();
		myExample.put("command","print");
		myExample.put("userid","vasia");
		myExample.put("sendmessage","hello");
		
		assertEquals(myHashFromExec, myExample);
		
	}
	
	@Test
	public final void testControlStart() {
		String args[]={"--cmd=start"}; //My parameters 
		
		ParsingRegulary exec=new ParsingRegulary(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);
		Map<String,String> myExample= new LinkedHashMap<>();
		myExample.put("command","start");
		
		assertEquals(myHashFromExec, myExample);
		
	}
	
	@Test
	public final void testControlStop() {
		String args[]={"--cmd=stop"}; //My parameters 
		
		ParsingRegulary exec=new ParsingRegulary(); 
		Map<String,String> myHashFromExec=exec.decodeToMap(args);
		Map<String,String> myExample= new LinkedHashMap<>();
		myExample.put("command","stop");
		
		assertEquals(myHashFromExec, myExample);
		
	}

}

