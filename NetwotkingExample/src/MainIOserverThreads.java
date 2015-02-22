
public class MainIOserverThreads {

	public static void main(String[] args) throws InterruptedException {
		int portNumber = 4538;
		IOThreadPooledServer server = new IOThreadPooledServer(portNumber);
		Thread myThread= new Thread(server);
		myThread.start();
		myThread.join();
		System.out.println("Stopping Server");
		server.stop();
	}

}
