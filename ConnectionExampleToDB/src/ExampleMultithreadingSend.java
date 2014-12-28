import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExampleMultithreadingSend implements Runnable {
	ArrayList<String> list = new ArrayList<String>();

	ExampleMultithreadingSend(ArrayList<String> list) {
		this.list = list;
	}

	public void run() {

		for (int ind = 0; ind < 100; ind++) {
			synchronized (this.list) {
				list.add("Hello " + ind);
			}
		}
		synchronized (this.list) {
			list.add("Stop");
		}
	}

}