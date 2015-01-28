import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainThreadAccounts {
	static MoneyTransfer a;
	static Random rnd = new Random();
	Logger logger = Logger.getLogger(MainThreadAccounts.class.getName());

	public static void main(String[] args) {

		int[] accounts = new int[5];
		Object[] arrayObj = new Object[5];
		
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			accounts[i] = rnd.nextInt(1000);
			sum += accounts[i];
			arrayObj[i]=new Object();
			
		}
		Logger.getLogger(MainThreadAccounts.class.getName()).log(Level.INFO,
				"Our Main sum=" + sum);
		a = new MoneyTransfer(accounts,arrayObj);
		for (int i = 0; i < 15; i++) {
		Thread myThready = new Thread(a);
		myThready.start();
		}
	}

}

