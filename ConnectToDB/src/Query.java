import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;



public class Query {

	public static void executeQuery(Connection conn, String sqlQuery)
			throws SQLException {
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		}

	}

	public static ResultSet openQuery(Connection conn, String sqlQuery)
			throws SQLException {
		ResultSet rs = null;
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sqlQuery);

		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		}
		return rs;
	}

	@SuppressWarnings("rawtypes")
	public static void insertToDBfromMap(Connection conn,
			Table myStructureTable, String tableName) throws SQLException {
		try {

			String ourKey = "", ourValues = "", sqlQuery;

			Statement statement = conn.createStatement();

			for (Entry<String, Type> elem : myStructureTable
					.getTableStructure().entrySet()) {
				if (elem.getKey().equals("ID"))
					continue;
				ourKey = ourKey + elem.getKey() + ",";
				if (elem.getValue().tellType().contains("String"))
					ourValues = ourValues + "'" + elem.getValue().getVariable()
							+ "',";
				else if (elem.getValue().tellType().contains("Date")) {
					SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy.MM.dd");
					String stDate = "'"
							+ formatter2.format(elem.getValue().getVariable())
							+ "'";
					ourValues = ourValues + stDate + ",";
				} else
					ourValues = ourValues
							+ elem.getValue().getVariable().toString() + ",";

			}

			ourKey = ourKey.substring(0, ourKey.length() - 1);
			ourValues = ourValues.substring(0, ourValues.length() - 1);

			sqlQuery = "INSERT INTO " + tableName + " (" + ourKey
					+ ") values (" + ourValues + ")";
			statement.executeUpdate(sqlQuery);

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static Boolean controlUniqueRecordByField(Connection conn,
			String field, String tableName, Table myStructure)
			throws SQLException{
		ResultSet rs = null;
		try {
			
			String ourQuery = "Select * from " + tableName
					+ " where " + field + "=?";
			PreparedStatement  statement = conn.prepareStatement(ourQuery);
			statement.setString(1, myStructure.getTableStructure().get(field).getVariable().toString());
			rs=statement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Table returnRecordByID(Connection conn, int id,
			String tableName, Table myStructure) throws SQLException,
			ParseException {
		ResultSet rs = null;
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("Select * from " + tableName
					+ " where ID=" + id);

			while (rs.next()) {
				for (Entry<String, Type> elem : myStructure.getTableStructure()
						.entrySet()) {
					if (elem.getValue().tellType().contains("String"))
						elem.getValue()
								.setVariable(rs.getString(elem.getKey()));
					else if (elem.getValue().tellType().contains("Integer"))
						elem.getValue().setVariable(rs.getInt(elem.getKey()));
					else if (elem.getValue().tellType().contains("Double"))
						elem.getValue()
								.setVariable(rs.getDouble(elem.getKey()));
					else if (elem.getValue().tellType().contains("Date")) {
						elem.getValue().setVariable(rs.getDate(elem.getKey()));
					}
				}

			}

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			throw e;
		}

		return myStructure;
	}

}
