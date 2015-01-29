package TenMillion;
 

import java.util.Map;
 






import org.junit.Test;
 






import junit.framework.TestCase;
 
 
public class test extends TestCase {
@Test 
public void testTenMillion() throws InterruptedException {
	tenMillionCounter.main(null);
	int expected = 10000000;
	int actual = oneMillion.x;
	assertEquals(expected, actual);
	}

}



