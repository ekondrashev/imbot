package TenMillion;

import org.junit.Test;

import junit.framework.TestCase;

public class test extends TestCase {

	@Test
	public void testTenMillionLatch() throws InterruptedException {
		OneMillion.x = 0;
		TenMillionLatch.main(null);
		int expected = 10000000;
		int actual = OneMillion.x;
		assertEquals(expected, actual);
	}
	@Test
	public void testTenMillion() throws InterruptedException {
		OneMillion.x = 0;
		TenMillionCounter.main(null);
		int expected = 10000000;
		int actual = OneMillion.x;
		assertEquals(expected, actual);
	}
}
