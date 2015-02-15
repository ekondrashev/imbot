import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Telnet {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		int portNumber =Integer.parseInt("9000");
		
		try{
			serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			System.out.println("Got connection");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String str;
			while (!(str = in.readLine()).equals("exit")){
				System.out.println("Got msg: " + str );
			out.write("You wrote: " + str);
			out.flush();
			}
					
		} finally {
			if (serverSocket != null)
				serverSocket.close();
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		}
	}
}
