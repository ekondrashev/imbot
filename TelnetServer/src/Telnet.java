import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Telnet {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null, serverSocket2 = null;
		PrintWriter out = null, out2 = null;
		BufferedReader in = null, in2 = null;
		
		int portNumber = Integer.parseInt("9000");
		int portNumber2 = Integer.parseInt("9001");
		
		try{
			serverSocket = new ServerSocket(portNumber);
			serverSocket2 = new ServerSocket(portNumber2);
			Socket clientSocket = serverSocket.accept();
			Socket clientSocket2 = serverSocket2.accept();
			System.out.println("Got connection");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			out2 = new PrintWriter(clientSocket2.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			in2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
			String str = ""; 
			String str2 = "";
			while (!(str = in.readLine()).equals("exit") || !(str2 = in2.readLine()).equals("exit")){
				//System.out.println("Got msg: " + str );
			out.write("You wrote: " + str + str2 + "\n\r");
			out2.write("You wrote: " + str + str2 + "\n\r");
			out.flush();
			out2.flush();
			}
					
		} finally {
			if (serverSocket != null)
				serverSocket.close();
			if (serverSocket2 != null)
				serverSocket2.close();
			if (out != null)
				out.close();
			if (out2 != null)
				out2.close();
			if (in != null)
				in.close();
			if (in2 != null)
				in2.close();
		}
	}
}
