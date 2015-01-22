public class ThreadCounter implements Runnable {

	private volatile IntegerCount count;

	ThreadCounter(IntegerCount count) {
		this.count = count;
	}

	@Override
	public void run() {
		
		while (count.count < 1000000) {
					 synchronized (count){ 
					count.count++;
				// Thread.currentThread();
				// Thread.sleep(10);
					System.out.println(Thread.currentThread().getName() + " "
							+ count.count);
			
				}
			}
		}
	
}
