 
import java.util.HashMap;
import java.util.Map;
 

import org.junit.Test;
 

import junit.framework.TestCase;
 
 
public class test extends TestCase {
 //@Test 
public void _testStartCmdLineOption() {
        String[] args = new String[]{"--cmd=start"};
        Map<String, String> expected = new HashMap<>();
        expected.put("cmd", "start");
        //TODO
       
        Map<String, String> actual = Exec.myParsingMethod(args);
       
        assertEquals (expected, actual);
}
//@Test
public void _testStopCmdLineOption() {
        String[] args = new String[]{"--cmd=stop"};
        Map<String, String> expected = new HashMap<>();
        expected.put("cmd", "stop");
        //TODO
       
        Map<String, String> actual = Exec.myParsingMethod(args);
       
        assertEquals (expected, actual);
}
@Test
public void testSendMessage() {
	    String[] args = new String[]{"--cmd=Send_message", "--userid=Vasya", "--message=\"hello\""};
	    Map<String, String> expected = new HashMap<>();
	    expected.put("cmd", "Send_message");
	    expected.put("userid", "Vasya");
	    expected.put("message", "\"Hello\"");
	    
	    Map<String, String> actual = Exec.myParsingMethod(args);
	    
	    assertEquals (expected, actual);
}
}
