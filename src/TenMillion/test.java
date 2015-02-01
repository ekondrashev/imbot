package TenMillion;

import org.junit.Test;

import junit.framework.TestCase;

public class test extends TestCase {

	@Test
	public void testTenMillionLatch() throws InterruptedException {
		oneMillion.x = 0;
		TenMillionLatch.main(null);
		int expected = 10000000;
		int actual = oneMillion.x;
		assertEquals(expected, actual);
	}
	@Test
	public void testTenMillion() throws InterruptedException {
		oneMillion.x = 0;
		tenMillionCounter.main(null);
		int expected = 10000000;
		int actual = oneMillion.x;
		assertEquals(expected, actual);
	}
}
