import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainThreadAccounts {
	static MoneyTransfer a;
	static Random rnd = new Random();
	Logger logger = Logger.getLogger(MainThreadAccounts.class.getName());

	public static void main(String[] args) {

		int[] accounts = new int[5];
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			accounts[i] = rnd.nextInt(1000);
			//Logger.getLogger(AccountMain.class.getName()).log(Level.INFO,
			//		"accountsSUM1=" + accounts[i]);
			sum += accounts[i];
		}
		Logger.getLogger(MainThreadAccounts.class.getName()).log(Level.INFO,
				"Our Main sum=" + sum);
		a = new MoneyTransfer(accounts);
		for (int i = 0; i < 15; i++) {
		Thread myThready = new Thread(a);
		myThready.start();
		}
	}

}

