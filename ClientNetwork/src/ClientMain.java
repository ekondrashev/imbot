import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientMain {

	public static void main(String[] args) throws IOException {
		int portNumber = 4567;
		
		Socket serverSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		Socket clientSocket = null;
		BufferedReader inu=null;
		
		try {
			serverSocket = new Socket("localhost",portNumber);
			
			while(true) {

			out = new PrintWriter(serverSocket.getOutputStream(), true);

			in = new BufferedReader(new InputStreamReader(
					serverSocket.getInputStream()));
			
			inu = new BufferedReader(new InputStreamReader(System.in));
			
			String fuser,fserver;
			
			System.out.println("Ready");
			
			try {
				while ((fuser = inu.readLine())!=null) {
				      out.println(fuser);
				      fserver = in.readLine();
				      System.out.println(fserver);
				      if (fuser.equalsIgnoreCase("exit")) break;
				    }
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			
		}

		finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (clientSocket != null)
				clientSocket.close();
			if (serverSocket != null)
				serverSocket.close();
		}
	}

	}

