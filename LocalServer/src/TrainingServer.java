import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class TrainingServer implements Runnable {
    private int port;
    private String clientName;

    public TrainingServer(int port) {
	this.port = port;
    }

    @Override
    public void run() {
	HashMap<String, Socket> list = new HashMap<String, Socket>();
	System.out.println("Server is running and waits for a connection .");
	try (ServerSocket serverSocket = new ServerSocket(port)) {
	    while (!Thread.currentThread().isInterrupted()) {
		Socket clientSocket = serverSocket.accept();
		clientName = clientSocket.toString();
		list.put(clientName, clientSocket);
		System.out.println(clientName + " has connected");
		new Thread(new Clients(list, clientName)).start();
	    }
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
    }
}
