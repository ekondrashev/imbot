import java.util.ArrayList;

public class ExampleMultithreadingGet implements Runnable {
	ArrayList<String> list = new ArrayList<String>();

	ExampleMultithreadingGet(ArrayList<String> list) {
		this.list = list;
	}

	public void run() {
		synchronized (this.list) {
			writerList();
		}
	}

	 public void writerList() {
		if (!this.list.isEmpty()) {
			while (!Thread.currentThread().isInterrupted()) {
				for (String arg : this.list) {
					if (arg != "Stop")
						System.out.println(arg);
					else
						Thread.currentThread().interrupt();
				}
			}
		}
	}

}
