import java.util.Random;

public class Account implements Runnable {
	private static final Object monitor = new Object();
	private int accounts[];

	Account(int accounts[]) {
		this.accounts = accounts;

	}

	public void transfer() {
		int trans = 0;
		while (!Thread.currentThread().isInterrupted()) {
			synchronized (monitor) {
				int firstAccountNumber = new Random().nextInt(4);
				int secondAccountNumber = new Random().nextInt(4);

				if (firstAccountNumber == secondAccountNumber)
					continue;
				else {
					int summForTransfer = new Random().nextInt(1000);

					if (accounts[firstAccountNumber] < summForTransfer) {
						System.out.println("No money for transfer on account "
								+ firstAccountNumber + ". Account have only:"
								+ accounts[firstAccountNumber] + " thread "
								+ Thread.currentThread().getName());
						continue;
					} else {
						accounts[secondAccountNumber] += summForTransfer;
						accounts[firstAccountNumber] -= summForTransfer;
						System.out.println("Transfer complete from "
								+ firstAccountNumber + " to "
								+ secondAccountNumber + ". Summ "
								+ summForTransfer + " thread "
								+ Thread.currentThread().getName());
					}
				}

				trans++;
				if (trans == 100)
					Thread.currentThread().interrupt();
				
				monitor.notifyAll();
			}
			
		}
	}

	@Override
	public void run() {
		transfer();
	}

	public int[] setArray() {
		return this.accounts;

	}
}
