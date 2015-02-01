import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoneyTransfer implements Runnable {
	int[] accounts;
	Object[] arrayObj = new Object[5];

	int counter1;
	int counter2;
	static int rndSum;
	static Random rnd = new Random();
	Logger logger = Logger.getLogger(MoneyTransfer.class.getName());

	MoneyTransfer(int[] account, Object[] arrayObj) {
		this.accounts = account;
		this.arrayObj = arrayObj;
	}

	public void run() {

		int sum;
		int counter=0;

		while (!Thread.currentThread().isInterrupted()) {
			sum = 0;
			counter++;
			counter1 = getRndArrayInd();
			counter2 = getRndArrayInd();

			if (counter2 == counter1) {
				while (!(counter2 == counter1)) {
					// System.out.println("Our counters are  equals");
					counter2 = getRndArrayInd();
				}
				// System.out.println("Our new 2 ind=" + counter2);

			}

			synchronized (arrayObj[counter1]) {
				Logger.getLogger(MoneyTransfer.class.getName())
						.log(Level.INFO,
								Thread.currentThread().getName() + " "
										+ "Our 1 ind=" + counter1
										+ " where sum=" + accounts[counter1]);
				synchronized (arrayObj[counter2]) {
					Logger.getLogger(MoneyTransfer.class.getName()).log(
							Level.INFO,
							Thread.currentThread().getName() + " "
									+ "Our 2 ind=" + counter2 + " where sum="
									+ accounts[counter2]);
					do {
						getRndSum();
						Logger.getLogger(MoneyTransfer.class.getName()).log(
								Level.INFO,
								Thread.currentThread().getName() + " "
										+ "Our Random sum= " + rndSum);
					} while (accounts[counter2] < rndSum);

					accounts[counter2] -= rndSum;
					Logger.getLogger(MoneyTransfer.class.getName()).log(
							Level.INFO,
							Thread.currentThread().getName() + " "
									+ "Our decCouter ind=" + counter2
									+ ", where new sum=" + accounts[counter2]);

					accounts[counter1] += rndSum;
					Logger.getLogger(MoneyTransfer.class.getName()).log(
							Level.INFO,
							Thread.currentThread().getName() + " "
									+ "Our incCouter ind=" + counter1
									+ ", where new sum=" + accounts[counter1]);

					for (int i = 0; i < accounts.length; i++) {
						sum += accounts[i];
					}
					Logger.getLogger(MoneyTransfer.class.getName()).log(
							Level.INFO,
							Thread.currentThread().getName() + " "
									+ "Our Tread sum=" + sum);
				}
			}
			if (counter==1000) Thread.currentThread().interrupt();
			arrayObj[counter2].notifyAll();
			arrayObj[counter1].notifyAll();
		}
	}

	static int getRndArrayInd() {
		return rnd.nextInt(5);
	}

	static void getRndSum() {
		rndSum = rnd.nextInt(100);
	}

}
