import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;


public class MainClass {

	private static final Logger logger = Logger.getLogger(MainClass.class.getName());
	
	public static void main(String[] args) {
		Int dig = new Int();
		Semaphore postovoy = new Semaphore(1);
		CountDownLatch cdl = new CountDownLatch(10);
		logger.info("dig = " + dig.i);
		IteratorClass iter = new IteratorClass(postovoy, cdl, dig);
		for (int i = 0; i < 10; i++) {
			new Thread(iter).start();
		}

		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("dig = " + dig.i);
	}

}
