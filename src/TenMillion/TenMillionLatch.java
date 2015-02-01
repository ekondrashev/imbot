package TenMillion;

import java.util.concurrent.CountDownLatch;

public class TenMillionLatch extends Thread {
	public static void main(String[] args) throws InterruptedException {
		final OneMillion tenThreads = new OneMillion();

		final CountDownLatch doneSignal = new CountDownLatch(10);

		for (int i = 0; i < 10; ++i) {
			// create and start threads

			Thread thready = new Thread() {
				public void run() {
					synchronized (tenThreads) {
						tenThreads.millionCount();
						
					}
					doneSignal.countDown();

				}

			};

			thready.start();

		}
		doneSignal.await();

	}

}
