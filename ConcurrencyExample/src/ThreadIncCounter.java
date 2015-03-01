import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadIncCounter implements Runnable {
	AtomicInteger counter = new AtomicInteger();


	public ThreadIncCounter(AtomicInteger counter) {
		this.counter = counter;
	}

	public void run() {

		for (int ind = 0; ind < 1000000; ind++) {
			counter.getAndIncrement();
			
			Logger.getLogger(ThreadIncCounter.class.getName()).log(Level.INFO,
					Thread.currentThread().getName() + " " + counter);
		}


	}
}