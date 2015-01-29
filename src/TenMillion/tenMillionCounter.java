package TenMillion;

import java.util.LinkedHashSet;

public class tenMillionCounter extends Thread {
	public static void main(String[] args) throws InterruptedException {
		final oneMillion tenThreads = new oneMillion();

		Thread thready = null;
		LinkedHashSet<Thread> container = new LinkedHashSet<>();
		 container.add(thready);
		for (int i = 0; i < 10; i++) {
			thready = new Thread() {
				public void run() {
					synchronized (tenThreads) {
						tenThreads.millionCount();
					}

				}
				 container.add(thready);

			};
		

			thready.start();

			for (int j = 0; j < 10; j++) {

				thready.join();
			}

		}
	}
}
