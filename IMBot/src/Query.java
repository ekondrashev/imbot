import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map.Entry;

public class Query {

	public static void executeQuery(Connection conn, String sqlQuery)
			throws SQLException {
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			System.out.println(e);
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
		}
		return rs;
	}

	@SuppressWarnings("rawtypes")
	public static void insertToDBfromMap(Connection conn, Table myStructureTable,
			String tableName) throws SQLException {
		try {

			String ourKey = "(", ourValues = "(", sqlQuery;

			Statement statement = conn.createStatement();

			for (Entry<String, Type> elem : myStructureTable
					.getTableStructure().entrySet()) {
				ourKey = ourKey + elem.getKey() + ",";
				if (elem.getValue().tellType().contains("String"))
					ourValues = ourValues + "'" + elem.getValue().getVariable()	+ "',";
				else
					ourValues = ourValues + elem.getValue().getVariable() + ",";

			}

			ourKey = ourKey.substring(ourKey.length() - 1) + ")";
			ourValues = ourValues.substring(ourValues.length() - 1) + ")";

			sqlQuery = "INSERT INTO " + tableName + ourKey + " values "
					+ ourValues;
			statement.executeUpdate(sqlQuery);

		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Table returnRecordByID(Connection conn, int id, String tableName, Table myStructure)
			throws SQLException {
		ResultSet rs = null;
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("Select * from "+tableName+" where idtovar="+id);
			
			  while (rs.next()) {
				  for (Entry<String, Type> elem : myStructure.getTableStructure().entrySet()) 
					{
					  if (elem.getValue().tellType().contains("String"))
						  elem.getValue().setVariable(rs.getString(elem.getKey()));
					  else if (elem.getValue().tellType().contains("Integer"))
						  elem.getValue().setVariable(rs.getInt(elem.getKey()));  
					  else if (elem.getValue().tellType().contains("Double"))
						  elem.getValue().setVariable(rs.getDouble(elem.getKey()));
					  else if (elem.getValue().tellType().contains("Date")) 
						  elem.getValue().setVariable(rs.getDate(elem.getKey()));
					}	
				  	    	
			    }

		} catch (SQLException e) {
			System.out.println(e);
		}
		return myStructure;
	}
	
}