import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;


public class BfIn implements Runnable{
    private static Logger log = Logger.getLogger(TrainingServer.class.getName());
    private BufferedReader in = null;

    public BfIn(BufferedReader in) {
	this.in = in;
    }
    
    @Override
    public void run() {
	log.info("BfLnThread is running");
	try {
	    while (!Thread.currentThread().isInterrupted()) {
		System.out.println(in.readLine());
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
