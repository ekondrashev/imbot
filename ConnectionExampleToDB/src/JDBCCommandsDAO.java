import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class JDBCCommandsDAO implements CommandsDAO {
	Connection connection = null;
	String user = "root";
	String password = "111111";
	String url = "jdbc:mysql://192.168.5.192/imbot";

	public void establishConnection() throws ClassNotFoundException,
			SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (connection == null)
				connection = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			throw e;

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void insert(Commands cmd) throws SQLException {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO commands (id ,name) VALUES (NULL , ?)");
			preparedStatement.setString(1, cmd.getName());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<Commands> select() throws SQLException {
		List<Commands> commands = new LinkedList<Commands>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM commands");

			Commands cmd = null;
			while (resultSet.next()) {
				cmd = new Commands();
				// cmd.setId(Integer.parseInt(resultSet.getString("id")));
				cmd.setName(resultSet.getString("name"));

				commands.add(cmd);
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println(commands);
		return commands;
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// do nothing
		}
	}

	@Override
	public Boolean isCommandExist(String name) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			preparedStatement = connection
					.prepareStatement("SELECT * FROM commands where name=?");
			preparedStatement.setString(1, name);
			result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
