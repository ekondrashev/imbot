import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestTransport {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Transport t = new Transport("from=������, ������������ �������� 4 to=���������� ���������");
		assertEquals(t.from, "������, ������������ �������� 4 ");
		assertEquals(t.to, "���������� ���������");
	}

}
