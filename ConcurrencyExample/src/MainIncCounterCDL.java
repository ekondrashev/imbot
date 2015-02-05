import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


public class MainIncCounterCDL {
	static AtomicInteger counter = new AtomicInteger();
	static ThreadIncCounterCDL threadIncCounter;

	
    public static void main(String[] args) throws InterruptedException {
		CountDownLatch counterCDL = new CountDownLatch(10);
		threadIncCounter = new ThreadIncCounterCDL(counter,counterCDL);
		
		Thread t[] = new Thread[10];
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new Thread(threadIncCounter), "Поток " + i);

			t[i].start();
		}
		
		counterCDL.await();
		
	}

}