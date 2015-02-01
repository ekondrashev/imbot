import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoneyTransfer implements Runnable {
	Logger log = Logger.getLogger(MoneyTransfer.class.getName());
	int[] accounts;
	Object[] arrayObj = new Object[5];
	public static int sum;
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
		int counter = 0;

		while (!Thread.currentThread().isInterrupted()) {

			counter++;
			counter1 = getRndArrayInd();
			counter2 = getRndArrayInd();
			Object loc1;
			Object loc2;

			if (counter2 == counter1) {
				while (!(counter2 == counter1)) {
					// System.out.println("Our counters are  equals");
					counter2 = getRndArrayInd();
				}
				// System.out.println("Our new 2 ind=" + counter2);

			}

			if (counter1 < counter2) {
				loc1 = arrayObj[counter1];
				loc2 = arrayObj[counter2];
			} else {
				loc1 = arrayObj[counter2];
				loc2 = arrayObj[counter1];
			}

			synchronized (loc1) {
//				log.log(Level.INFO, Thread.currentThread().getName() + " "
//						+ "Our 1 ind=" + counter1 + " where sum="
//						+ accounts[counter1]);
				synchronized (loc2) {
//					log.log(Level.INFO, Thread.currentThread().getName() + " "
//							+ "Our 2 ind=" + counter2 + " where sum="
//							+ accounts[counter2]);
					do {
						getRndSum();
//						log.log(Level.INFO, Thread.currentThread().getName()
//								+ " " + "Our Random sum= " + rndSum);
					} while (accounts[counter2] < rndSum);

					accounts[counter2] -= rndSum;
//					log.log(Level.INFO, Thread.currentThread().getName() + " "
//							+ "Our decCouter ind=" + counter2
//							+ ", where new sum=" + accounts[counter2]);

					accounts[counter1] += rndSum;
//					log.log(Level.INFO, Thread.currentThread().getName() + " "
//							+ "Our incCouter ind=" + counter1
//							+ ", where new sum=" + accounts[counter1]);

				}
			}
			if (counter == 100)
				Thread.currentThread().interrupt();

		}

//		sum = getEndSum(accounts);
//		
//		log.log(Level.INFO, Thread.currentThread().getName() + " "
//				+ "Our Tread sum=" + sum);

	}

	static int getRndArrayInd() {
		return rnd.nextInt(5);
	}

	static void getRndSum() {
		rndSum = rnd.nextInt(100);
	}

//	int getEndSum(int[] accounts) {
//		int sumEnd = 0;
//		for (int i = 0; i < accounts.length; i++) {
//			sumEnd += accounts[i];
//		}
//		return sumEnd;
//
//	}

}
