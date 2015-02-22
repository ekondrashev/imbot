import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;


public class MyHear implements Runnable {
	
	
void hear() throws IOException, InterruptedException{
	int portNumber = 4567;
	
	ServerSocket serverSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	Socket clientSocket = null;
	
	try {
		serverSocket = new ServerSocket(portNumber);
		
		while(true) {
		clientSocket = serverSocket.accept();

		out = new PrintWriter(clientSocket.getOutputStream(), true);

		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		
		Thread myThread = new Thread(new WorkWithSocket(serverSocket, out, in, clientSocket));
		myThread.start();
		}
		
	}

	finally {
		if (out != null)
			out.close();
		if (in != null)
			in.close();
		if (clientSocket != null)
			clientSocket.close();
		if (serverSocket != null)
			serverSocket.close();
	}
}

	@Override
	public void run() {
		try {
			hear();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

