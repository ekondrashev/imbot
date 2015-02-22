import java.io.*;
import java.net.*;

public class NetClient {
	public static void main(String[] args) throws IOException {
		int portNumber = 4538;
		String address = "127.0.0.1";

		System.out.println("Welcome to Client side");

		Socket fromserver = null;
		try {
			InetAddress ipAddress = InetAddress.getByName(address);
			fromserver = new Socket(ipAddress, portNumber);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					fromserver.getInputStream()));
			PrintWriter out = new PrintWriter(fromserver.getOutputStream(),
					true);
			BufferedReader inu = new BufferedReader(new InputStreamReader(
					System.in));

			String fuser, fserver;

			while ((fuser = inu.readLine()) != null) {
				out.println(fuser);
				fserver = in.readLine();
				System.out.println(fserver);
				if (fuser.equalsIgnoreCase("exit"))
					break;
			}

			out.close();
			
			
			in.close();
			inu.close();
			fromserver.close();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}
