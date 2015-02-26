import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TrainingClient implements Runnable {;
    private int port;
    private String host;
    protected String userName;

    public TrainingClient(String host, int port, String user) {
	this.port = port;
	userName = user;
	this.host = host;
    }

    @Override
    public void run() {
	try (Socket clientSocket = new Socket(host, port)) {
	    try (BufferedReader localReader = new BufferedReader(
		    new InputStreamReader(System.in));) {
		System.out.println("You have connected to "
			+ clientSocket.toString());
		try (// BufferedReader in = new BufferedReader(new
		     // InputStreamReader(
		     // clientSocket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
			clientSocket.getOutputStream()), true)) {

		    // new Thread(new BfIn(in)).start();
		    String str = null;
		    while (!(str = localReader.readLine()).equals("exit")) {
			out.println(userName + " - " + str);
		    }
		    System.out.println("Good Bye");
		}
	    }
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
