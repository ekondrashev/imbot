import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;


public class ThreadIncCounterTest {

	@Test
	public void test() throws InterruptedException {
		Main.main(null);
		assertEquals(Main.counter, 10000000);
		
	}

}
