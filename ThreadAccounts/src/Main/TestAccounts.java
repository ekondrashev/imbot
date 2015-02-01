package Main;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAccounts {
	
		Account[] accounts = new Account[10];
		Random rand = new Random();
		Transfer t = new Transfer(accounts);

		public static int summArray(Account[] arr){
			int result = 0;
			for (Account account : arr) {
				result += account.i;
			}
			return result;
		}
		

	@Before
	public void setUp() throws Exception {
		
		for (int k = 0; k < accounts.length; k++) {
			accounts[k] = new Account(rand.nextInt(100000));
		}	
		
	}


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		int expected = summArray(accounts);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 1000; j++) {
			new Thread(t).run();
			}
			assertEquals(expected, summArray(accounts));
		}
		
	}

}
