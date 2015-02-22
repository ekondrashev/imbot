import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class WorkWithSocket implements Runnable{
	ServerSocket serverSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	Socket clientSocket = null;
	
	public WorkWithSocket(ServerSocket serverSocket,PrintWriter out,BufferedReader in,Socket clientSocket) {
		this.serverSocket=serverSocket;
		this.out=out;
		this.in=in;
		this.clientSocket=clientSocket;

	}
	
	void ourStream() throws IOException{
		String input;
		System.out.println("Wait for messages");
		try {
			while ((input = in.readLine()) != null) {
				if (input.equalsIgnoreCase("exit"))
					break;
				out.println("Mirror:" + input);
				System.out.println(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
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
	
	
	@Override
	public void run(){
		try {
			ourStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
