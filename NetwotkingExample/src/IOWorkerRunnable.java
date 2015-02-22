import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class IOWorkerRunnable implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;

	public IOWorkerRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		String inRead;
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			
				out.println("Hello");

				while ((inRead = in.readLine()) != null)
					if (!inRead.startsWith("exit"))
						out.println("Return mess: " + inRead);
					else
						break;
				out.close();
				in.close();
				clientSocket.close();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
