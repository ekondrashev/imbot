import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;


public class MainClass {

	private static final Logger logger = Logger.getLogger(MainClass.class.getName());
	
	public static void main(String[] args) {
		Int dig = new Int();
		CountDownLatch cdl = new CountDownLatch(10);
		logger.info("dig = " + dig.i);
		IteratorClass iter = new IteratorClass(cdl, dig);
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
