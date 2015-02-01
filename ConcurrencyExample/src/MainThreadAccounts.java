import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainThreadAccounts {
	static MoneyTransfer a;
	static Random rnd = new Random();
	Logger logger = Logger.getLogger(MainThreadAccounts.class.getName());
	public static int sum;
	public static int sumEnd;
	public static void main(String[] args) throws InterruptedException {
		Thread[] myThreadysArray = new Thread[5];
		int[] accounts = new int[5];
		Object[] arrayObj = new Object[5];

		for (int i = 0; i < 5; i++) {
			accounts[i] = rnd.nextInt(1000);
			 sum += accounts[i];
			arrayObj[i] = new Object();
		}

		//sum = getSum(accounts);
		Logger.getLogger(MainThreadAccounts.class.getName()).log(Level.INFO,
				"Our Main sum=" + sum);

		a = new MoneyTransfer(accounts, arrayObj);

		for (int i = 0; i < 5; i++) {
			myThreadysArray[i] = new Thread(a);
			myThreadysArray[i].start();
		}

		for (int i = 0; i < 5; i++) {
			myThreadysArray[i].join();
		}
		
		sumEnd = getSum(accounts);
		Logger.getLogger(MainThreadAccounts.class.getName()).log(Level.INFO,
				"Our Main sumEnd=" + sumEnd);
	}

	static int getSum(int[] accounts) {
		int sumStart = 0;
		for (int i = 0; i < 5; i++) {
			sumStart += accounts[i];
		}
		return sumStart;

	}
}
