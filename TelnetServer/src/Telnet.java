import java.io.IOException;



public class Telnet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			new Thread(new Connection(9000)).start();
			new Thread(new Connection(9001)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
