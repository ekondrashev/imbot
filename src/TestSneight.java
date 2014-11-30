import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;


public class TestSneight extends TestCase{

	@Test
	public final void testDecodeToMap() {
		
		String args[]={"--cmd=atata","-user_id=Sneight","-password=111222"};
		
		Map<String,String> HashSneight=Sneight.DecodeToMap(args);

		Map<String,String> Example= new LinkedHashMap<>();

		Example.put("cmd","atata");
		Example.put("user_id","Sneight");
		Example.put("password","111222");
		
		
		assertEquals(HashSneight, Example);
		
	}
	
	@Test
	public final void testControlHelp() {
		
		String args[]={"--help"};
		String USAGE = "Usage: Exec --cmd=<os_cmd> -user_id=<значение> -message=<значение>";
		
		Map<String,String> HashFromExec=Sneight.DecodeToMap(args);

		Map<String,String> myExample= new LinkedHashMap<>();

		myExample.put("help",USAGE);
		
		assertEquals(HashFromExec, myExample);
		
	}


}