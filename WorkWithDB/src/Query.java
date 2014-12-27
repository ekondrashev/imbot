import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {


	public static String askToQuestion(ConnectDB conn, String APIName,
			String Question) throws SQLException {
		ResultSet rs = null;
		Connection con = null;
		try {

			String query = new StringBuilder()
					.append("SELECT ")
					.append("Name  FROM  answers ")
					.append("WHERE  IDAPICom = (SELECT ")
					.append("apicommands.ID  FROM ")
					.append("apicommands  LEFT JOIN ")
					.append("commands ON apicommands.IDCom = commands.ID ")
					.append(" LEFT JOIN")
					.append(" apiname ON apicommands.IDAPI = apiname.ID ")
					.append(" WHERE ")
					.append("apiname.name = ? AND commands.name = ?)")
					.toString();
			con=conn.openConnect();
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, APIName);
			statement.setString(2, Question.replaceAll("\"", ""));
			rs = statement.executeQuery();
			
			if (rs.next()) {
				return rs.getString("Name");
			}

		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		}
		finally
		{
			conn.closeConnection(con);
		}
		return "Command is not valid!!!";
	}

}
