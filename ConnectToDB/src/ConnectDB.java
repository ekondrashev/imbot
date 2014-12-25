import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private String userName;
	private String password;
	private String url;

	ConnectDB(String url, String user, String password) {
		this.userName = user;
		this.password = password;
		this.url = url;
	}

	public Connection openConnect() throws SQLException,
			ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(this.url, this.userName,
					this.password);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void closeConnection(Connection conn) throws SQLException {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			conn = null;
			throw e;
		}
	}

	
}
