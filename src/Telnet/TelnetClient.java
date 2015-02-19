package Telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TelnetClient {
	public static void main(String args[]) throws Exception {

		Socket clientSocket = new Socket("localhost", 8090);
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		{

			try {
				out.println("Hello");
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (clientSocket != null)
						clientSocket.close();
					if (out != null)
						out.close();
					if (in != null)
						in.close();

				} catch (IOException ex) {
					System.out.println("Error" + ex);
				}

			}
		}

	}
}
