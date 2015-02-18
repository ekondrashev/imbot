import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Connection implements Runnable {
	Logger log = Logger.getLogger(Connection.class.getName());
	protected ServerSocket serverSocket = null;
	protected PrintWriter out = null;
	protected BufferedReader in = null;

	public Connection(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		Socket clientSocket = serverSocket.accept();
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String str = null;
		try {
			while (!(str = in.readLine()).equals("exit")) {
				out.write("You wrote: " + str + "\n\r");
				out.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			if (serverSocket != null)
				serverSocket.close();
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

	protected void finalize() throws IOException {
		if (serverSocket != null)
			serverSocket.close();
		if (out != null)
			out.close();
		if (in != null)
			in.close();
	}
}
