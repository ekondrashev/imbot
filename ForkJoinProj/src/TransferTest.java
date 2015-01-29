import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;


public class TransferTest {
	
	 public int summ(Account[] arr){
		int sum = 0;
		for (Account i : arr) {
			sum += i.money;
		}
		return sum;
	}
	 
	 @Test
	 public void multitest(){
		 for (int i = 0; i < 50; i++) {
			test();
		}
	 }


	public void test() {
		Random rand = new Random();
		Account[] accounts = new Account[10];
		for(int i = 0; i < accounts.length; i++){
			accounts[i] = new Account(rand.nextInt(1000));
		}
		int expected = summ(accounts);
		Transfer t = new Transfer(accounts);
		
		for (int i = 0; i < 10000; i++) {
			new Thread(t).start();
		}
		
		int actual = summ(accounts);
		assertEquals(expected, actual);
	}

}
