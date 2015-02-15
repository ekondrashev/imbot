package Telnet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TelnetServer {
	public static void main(String[] args) throws Exception {

//		int portNumber = Integer.parseInt(args[0]);
		int portNumber = 8088;
		ServerSocket serverSocket = new ServerSocket(portNumber);
		Socket clientSocket = serverSocket.accept();
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		{

			try {

				out.println("HelloWorld");
				 String line = in.readLine();
                out.println(line);
                
                     

			} finally {
				out.close();
				in.close();
			try{
				String line = in.readLine();
				if(line.equals("close"))
				
				
			
			}finally{
				serverSocket.close();
			}

		}
	}
}
}
