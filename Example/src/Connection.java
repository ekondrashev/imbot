import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {

	public static void main(String[] args) throws IOException {
		PrintWriter out = null;
		BufferedReader in = null;
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8089);
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			out.println("Hello client");
			
			
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			String a = in.readLine();
			out.println("m"+a);
			while () {
				
			}
		} catch (Exception e) {

		}
		finally {
			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();
		}
	}

}
