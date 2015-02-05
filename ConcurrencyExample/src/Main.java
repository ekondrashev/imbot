import java.util.concurrent.atomic.AtomicInteger;

public class Main {
	static AtomicInteger counter = new AtomicInteger();
	static ThreadIncCounter threadIncCounter;

	
    public static void main(String[] args) throws InterruptedException {
		threadIncCounter = new ThreadIncCounter(counter);

		Thread t[] = new Thread[10];
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new Thread(threadIncCounter), "����� " + i);

			t[i].start();
		}
		
		for (int i = 0; i < t.length; i++) {
			t[i].join();
		}
		

	}

}
