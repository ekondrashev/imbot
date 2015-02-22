import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetServer {
	public static void main(String[] args) throws IOException {
		String inRead;
		int portNumber = 4535;

		try {ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(
						clientSocket.getOutputStream(), true);
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
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + portNumber);
			System.exit(1);
		}

	}
}
