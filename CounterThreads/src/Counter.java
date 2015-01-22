public class Counter {

	public static void main(String[] args) throws InterruptedException {
		IntegerCount count = new IntegerCount(0);

		ThreadCounter ourCounter = new ThreadCounter(count);

		Thread[] thread = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(ourCounter);
			thread[i].setName("thread " + i);
			thread[i].start();
		}

		for (int i = 0; i < 10; i++)
			thread[i].join();

		System.out.println("Our counter " + count.count);

	}

}
