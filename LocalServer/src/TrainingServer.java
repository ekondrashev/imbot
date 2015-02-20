import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TrainingServer implements Runnable {
    Logger log = Logger.getLogger(TrainingServer.class.getName());
    int port;
    protected String userName;
    protected String clientName;

    protected TrainingServer() {

    }

    public TrainingServer(int port, String user) {
	this.port = port;
	userName = user;
    }

    @Override
    public void run() {
	
	try (ServerSocket serverSocket = new ServerSocket(port)) {
	    System.out
		    .println("Server is running and waits for a connection .");
	    //while (true) {
		try (Socket clientSocket = serverSocket.accept()) {
		    try (PrintWriter out = new PrintWriter(
			    clientSocket.getOutputStream(), true);
			    BufferedReader in = new BufferedReader(
				    new InputStreamReader(
					    clientSocket.getInputStream()));) {
			
			Thread listen = new Thread() {
			    public void run() {
				try {
				    while (true) {
					System.out.println(in.readLine());
				    }
				} catch (IOException e) {
				    // TODO Auto-generated catch block
				    System.out.println(e.getMessage());
				}
			    }
			};
			listen.start();

			Thread sender = new Thread() {
			    public void run() {
				BufferedReader localReader = new BufferedReader(
					new InputStreamReader(System.in));
				String str = null;
				try {
				    while (!(str = localReader.readLine())
					    .equals("exit")) {
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
		} catch (Exception e) {
		    e.printStackTrace();
		}
	  //  }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
