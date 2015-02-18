import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TrainingServer implements Runnable {
	Logger log = Logger.getLogger(TrainingServer.class.getName());
	protected ServerSocket serverSocket = null;
	protected Socket clientSocket = null;
	protected PrintWriter out = null;
	protected BufferedReader in = null;
	protected String userName;
	protected String clientName;

	protected TrainingServer() {

	}

	public TrainingServer(int port, String user) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server is running and waits for a connection .");
			clientSocket = serverSocket.accept();
			initOutInFields();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userName = user;
	}

	protected void initOutInFields() throws IOException {
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
	}

	@Override
	public void run() {
		out.println(userName); // Send my name to companion
		try {
			clientName = in.readLine();// Get companion's name
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		System.out.println("Let's speak with " + clientName);
		
		Thread listen = new Thread() {
			public void run() {
				try {
					while (true) {
						System.out.println(clientName + " - " + in.readLine());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		};
		listen.setDaemon(true);
		listen.start();

		Thread sender = new Thread() {
			public void run() {
				BufferedReader localReader = new BufferedReader(
						new InputStreamReader(System.in));
				String str = null;
				try {
					while (!(str = localReader.readLine()).equals("exit")) {
						out.println(str);
					}
					log.info("Close connection");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		sender.start();
	}

	protected void finalize() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (clientSocket != null)
				clientSocket.close();
			if (serverSocket != null)
				serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
