import java.util.concurrent.CountDownLatch;


public class IteratorClass implements Runnable {

	public Int dig;
	private CountDownLatch cdl;
	
	public IteratorClass(CountDownLatch cdl, Int b){
		this.cdl = cdl;
		this.dig = b;
	}
	public void iterator() {
		synchronized (dig) {
			for (int i = 0; i < 1000000; i++, dig.i++);
		}
	}
	public void run() {
		iterator();
		cdl.countDown();
	}
}
