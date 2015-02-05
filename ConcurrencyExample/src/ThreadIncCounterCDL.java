import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadIncCounterCDL implements Runnable {
	AtomicInteger counter = new AtomicInteger();
	CountDownLatch threadIncCounter;

	public ThreadIncCounterCDL(AtomicInteger counter,CountDownLatch threadIncCounter) {
		this.counter = counter;
		this.threadIncCounter = threadIncCounter;
	}

	public void run() {

		for (int ind = 0; ind < 1000000; ind++) {
			counter.getAndIncrement();
			
			Logger.getLogger(ThreadIncCounter.class.getName()).log(Level.INFO,
					Thread.currentThread().getName() + " " + counter);
		}

		threadIncCounter.countDown();
	}

	
}

