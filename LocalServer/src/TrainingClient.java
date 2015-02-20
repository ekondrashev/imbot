import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class TrainingClient implements Runnable{

    Logger log = Logger.getLogger(TrainingServer.class.getName());
    int port;
    String host;
    protected String userName;
    protected String clientName;

    public TrainingClient(String host, int port, String user) {
	this.port = port;
	userName = user;
	this.host = host;
    }

    @Override
    public void run() {

	try (Socket clientSocket = new Socket(host, port)) {
	    System.out.println("You have connected");
	    try (PrintWriter out = new PrintWriter(
		    clientSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
			    new InputStreamReader(clientSocket.getInputStream()));) {
		
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
    }
}
