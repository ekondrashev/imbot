import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class IteratorClass implements Runnable {

	public Int dig;
	private Semaphore s;
	CountDownLatch cdl;
	
	public IteratorClass(Semaphore s, CountDownLatch cdl, Int b){
		this.s = s;
		this.dig = b;
		this.cdl = cdl;
	}
	public void iterator() {
			for (int i = 0; i < 1000000; i++, dig.i++);
	}
	public void run() {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iterator();
		cdl.countDown();
		s.release();
	}
}
