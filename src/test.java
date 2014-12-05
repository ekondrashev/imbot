 
import java.util.HashMap;
import java.util.Map;
 

import org.junit.Test;
 

import junit.framework.TestCase;
 
 
public class test extends TestCase {
 @Test 
public void testStartCmdLineOption() {
        String[] args = new String[]{"--cmd=start"};
        Map<String, String> expected = new HashMap<>();
        expected.put("cmd", "start");
        //TODO
       
        Map<String, String> actual = Exec.myParsingMethod(args);
       
        assertEquals (actual, expected);
}
@Test
public void testStopCmdLineOption() {
        String[] args = new String[]{"--cmd=stop"};
        Map<String, String> expected = new HashMap<>();
        expected.put("cmd", "start");
        //TODO
       
        Map<String, String> actual = Exec.myParsingMethod(args);
       
        assertEquals (actual, expected);
}
}
