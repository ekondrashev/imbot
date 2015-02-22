
public class NetworkMain {

	public static void main(String[] args) throws InterruptedException  {

		Thread myThread = new Thread(new MyHear());
		myThread.start();
		myThread.join();
}
}