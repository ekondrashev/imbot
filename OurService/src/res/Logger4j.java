package res;


import org.apache.log4j.Logger;

public class Logger4j implements Runnable {
	private final Logger log = Logger.getLogger(Logger4j.class);

	void LogToFile() {
		log.info("Start processing");

		try {
			while (!Thread.currentThread().isInterrupted()) {
				log.info("I'm working. Hello.");
				Thread.sleep(20000);
				}
		} catch (Exception e) {
			log.error("Something failed", e);
		}
	}

	@Override
	public void run() {
		LogToFile();
	}

}
