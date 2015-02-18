import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TrainingClient extends TrainingServer {

	public TrainingClient(String host, int port, String user) {
		super();
		try {
			clientSocket = new Socket(host, port);
			initOutInFields();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You have connected to " + host);
		userName = user;
	}
}
