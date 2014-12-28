import java.util.ArrayList;

public class ExampleMultithreadingGet implements Runnable {
	ArrayList<String> list = new ArrayList<String>();

	ExampleMultithreadingGet(ArrayList<String> list) {
		this.list = list;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			for (String arg : list) {
				if (arg != "Stop")
					System.out.println(arg);
				else Thread.currentThread().interrupt();	
			}
		}
	}

}