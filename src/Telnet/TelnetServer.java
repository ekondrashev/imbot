package Telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TelnetServer {
	public static void main(String[] args) throws Exception {

		// int portNumber = Integer.parseInt(args[0]);
		int portNumber = 8090;
		ServerSocket serverSocket = new ServerSocket(portNumber);
		Socket clientSocket = serverSocket.accept();
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		{

			try {

			    out.println("Hello from TelnetServer");
				

				String line;
				while ((line = in.readLine())!= null) {
					out.println(line);
					System.out.println(line);
					if( line.equals("exit")) break;
					

				}

			} finally {
				try {
					if (out != null)
						out.close();
					if (in != null)
						in.close();
					if (serverSocket != null)
						serverSocket.close();
				} catch (IOException ex) {
					System.out.println("Error" + ex);
				}

			}
		}
	}
}
