import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private String userName;
	private String password;
	private String url;
	private Connection conn = null;

	ConnectDB(String url, String user, String password) {
		this.userName = user;
		this.password = password;
		this.url = url;
	}

	public Connection getConnectToDB() throws SQLException, Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection(this.url, this.userName, this.password);
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			conn = null;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			conn = null;
		}
		return this.conn;
	}

	public void closeConnection() throws SQLException {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
