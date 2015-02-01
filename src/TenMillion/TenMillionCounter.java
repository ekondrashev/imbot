package TenMillion;


import java.util.LinkedList;

public class TenMillionCounter extends Thread {
	public static void main(String[] args) throws InterruptedException {
		final OneMillion tenThreads = new OneMillion();

		Thread thready = null;
		LinkedList<Thread> container = new LinkedList<>();
	
		for (int i = 0; i < 10; i++) {
			thready = new Thread() {
				public void run() {
					synchronized (tenThreads) {
						tenThreads.millionCount();
					}

				}

			};
			container.add(thready);

			thready.start();

		}
		for (int i = 0; i<container.size(); i++){
			
			thready = container.get(i);
			thready.join();
		}
		
	}
}
