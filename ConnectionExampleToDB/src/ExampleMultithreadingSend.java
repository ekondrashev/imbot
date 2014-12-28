import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExampleMultithreadingSend implements Runnable {
	ArrayList<String> list = new ArrayList<String>();
	private Lock lock = new ReentrantLock();

	ExampleMultithreadingSend(ArrayList<String> list) {
		this.list = list;
	}

	public void run() {
		lock.lock();
		try {
			for (int ind = 0; ind < 100; ind++) {
				list.add("Hello " + ind);
			}
			list.add("Stop");
		} finally {
			lock.unlock();
		}
	}
}