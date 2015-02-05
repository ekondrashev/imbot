import static org.junit.Assert.*;

import org.junit.Test;

public class MoneyTransferTest {

	@Test
	public void test() throws InterruptedException {
		MainThreadAccounts.main(null);
		assertEquals(MainThreadAccounts.sum, MainThreadAccounts.sumEnd);

	}

}
