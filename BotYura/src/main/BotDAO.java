package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class BotDAO {
	private static final Logger logger = Logger.getLogger(BotYura.class.getName());
	
	private Connection con = null;
	//Constructor
	public BotDAO(String url, String login, String pass){
		
		String driver = "com.mysql.jdbc.Driver";
		
		try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, login, pass);

        } catch (ClassNotFoundException ex)
        {
            System.err.println("BotDAO.Cannot find this db driver classes.");
                ex.printStackTrace();
        } catch (SQLException e)
        {
            System.err.println("BotDAO.Cannot connect to this db.");
                e.printStackTrace();        
          }         
    }
	protected void finalize(){
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info(e.getMessage());
				e.printStackTrace();
			}
	}
	//this function show tables from DB
	public void ShowTables(){
		try
        {
                Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("show tables");
            while(rs.next()){
				System.out.println(rs.getString("tables_in_imbot"));
			}
            if(rs != null) rs.close();
            if(st != null) st.close();
         } catch (SQLException e)
         {
            System.err.println("KFDB.There are problems with the query " + "show tables");
            e.printStackTrace();
         }
	}
	//this function show the entire table 
	public void ShowEntireTable(String tableName){
		try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from " + tableName);
            
            ResultSetMetaData rsmd = rs.getMetaData(); 
        	int numberOfColumns = rsmd.getColumnCount();
        	
        	for (int i = 1; i <= numberOfColumns; i++) {
				System.out.printf("%20s", rsmd.getColumnName(i));
			}
        	System.out.print("\n");

            while (rs.next())
		      {
            	
            	for (int i = 1; i <= numberOfColumns; i++) {
            		System.out.printf("%20s", rs.getString(i));
				}
            	System.out.print("\n");
		      }
            
            if(rs != null) rs.close();
            if(st != null) st.close();
         } catch (SQLException e)
         {
            System.err.println("KFDB.There are problems with the query " + "show tables");
            e.printStackTrace();
         }
	}
	//this function to insert to a table one row
	public void InsertToTable(Map<String, String> mapValue, String tableName){
		try
        {
        	
        	String columnNames = "";
        	String columnValue = "";
        	
        	Set<String> keys = mapValue.keySet();
        	int i = 0;
        	
        	for (String key: keys) {
        		i++;
				if(i == keys.size()){
					columnNames += key;
					columnValue += "?";
				}
				else {
					columnNames += key + ", ";
					columnValue += "?, ";
				}
			}
        	
        	String query = "insert into " + tableName + " (" + columnNames + ") values (" + columnValue + ")";
        	logger.info(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            i = 1;
            for (String key: keys) {
            	preparedStmt.setString(i, mapValue.get(key));
            	i++;
			}
            
            preparedStmt.execute();
            
            if(preparedStmt != null) preparedStmt.close();

         } catch (SQLException e)
         {
            System.err.println("KFDB.There are problems with the query " + "show tables");
            e.printStackTrace();
         }
	}
	//this function check is command exist
	public Boolean IsCommandExist(String checkingCommand){
		
		Boolean result = false;
		try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select Name from Commands");
            while (rs.next())
		      {
            	if(checkingCommand.equals(rs.getString("Name"))){
            		result = true;
            		break;
            	}
		      }
            if(rs != null) rs.close();
            if(st != null) st.close();
         } 
		catch (SQLException e)
         {
            System.err.println("KFDB.There are problems with the query " + "show tables");
            e.printStackTrace();
         }
		return result;
	}
	//this function delete row from some table
	public void deleteRowFromTable(int delId, String tableName){
		
		try
        {
	      String query = "delete from " + tableName + " where id = ?";
	      PreparedStatement preparedStmt = con.prepareStatement(query);
	      preparedStmt.setInt(1, delId);
	 
	      preparedStmt.execute();
	      if(preparedStmt != null) preparedStmt.close();
        } 
		catch (SQLException e)
         {
            System.err.println("KFDB.There are problems with the query " + "show tables");
            e.printStackTrace();
         }
	}
}
