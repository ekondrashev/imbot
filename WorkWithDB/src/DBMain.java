import java.sql.SQLException;

import javax.swing.JOptionPane;


public class DBMain {

	public static void main(String[] args) throws InterruptedException, SQLException {
		
		String userName = "root";
		String password = "111111";
		String url = "jdbc:mysql://localhost/imbot";
		JOptionPane.showMessageDialog(null, "Start DB application");
		ConnectDB ourConn = new ConnectDB(url, userName, password); 
		
		Recv rdRecive = new Recv("ToDB","127.0.0.1",ourConn);
		Thread threadRecive = new Thread(rdRecive);
		threadRecive.setName("Recive");
		threadRecive.start();
		threadRecive.join();
	}

}
