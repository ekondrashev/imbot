package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BotDAO {
	private static final Logger logger = Logger.getLogger(BotYura.class.getName());
	
	private Connection con = null;
	private String url;
	private String login;
	private String pass;
	
	//Constructor
	public BotDAO(String url, String login, String pass) throws Exception{
		this.url = url;
		this.login = login;
		this.pass = pass;
		try {
			connection();
		} catch (ClassNotFoundException | SQLException e) {
			logger.log(Level.SEVERE, "BotDAO.Cannot connect to this db.", e);
			throw e;
		}
    }
	
	//Connection to Data Base
	private void connection() throws ClassNotFoundException, SQLException{
		String driver = "com.mysql.jdbc.Driver";
		try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, login, pass);

        } catch (ClassNotFoundException ex)
        {
            logger.log(Level.SEVERE, "BotDAO.Cannot find this db driver classes.", ex);
            throw ex;
        } catch (SQLException e)
        {
        	logger.log(Level.SEVERE, "BotDAO.Cannot connect to this db.", e);
        	throw e;
        } 
	}
	
	//Close connection by users
	public void closeConnection() throws SQLException{
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
	}
	
	//Close connection by garbage collector
	protected void finalize() throws SQLException{
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
	}
	
	//this function show tables from DB
	public void showTables() throws SQLException{
		Statement st = null;
        ResultSet rs = null;
        
		try
        {
            st = con.createStatement();
            rs = st.executeQuery("show tables");
            while(rs.next()){
				System.out.println(rs.getString(1));
			}
            
         } catch (SQLException e)
         {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
         } finally {
        	 if(rs != null) rs.close();
             if(st != null) st.close();
         }
	}
	
	//this function show the entire table 
	public void showEntireTable(String tableName) throws SQLException{
		
		Statement st = null;
        ResultSet rs = null;
        
		try
        {
            st = con.createStatement();
            rs = st.executeQuery("select * from " + tableName);
            
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
         } catch (SQLException e)
         {
        	 logger.log(Level.SEVERE, "BotDAO.There are problems with the query " + "select * from " + tableName, e);
             throw e;
         } finally {
        	 if(rs != null) rs.close();
             if(st != null) st.close();
         }
	}
	
//	this function to insert to a table one row. String[] columnValue - array containing the data will be added to the table 
	public void insertRowToTable(String tableName, String[] columnValue) throws SQLException{
		
		PreparedStatement preparedStmt = null;
		
		Statement st = null;
        ResultSet rs = null;
        
        StringBuffer columnNames = new StringBuffer();
        
		try
        {
            st = con.createStatement();
            rs = st.executeQuery("select * from " + tableName);
            
            ResultSetMetaData rsmd = rs.getMetaData(); 
        	int numberOfColumns = rsmd.getColumnCount(); 
        	
        	logger.info("Columns in table = " + numberOfColumns + ", in String[] = " + columnValue.length);
        	
        	if(numberOfColumns - 1 != columnValue.length){ // numberOfColumns without ID
        		logger.severe("Size of array String[] columnValue not equal amount columns in table excluding ID");
        		return;
        	}
        	
        	int i = 0;
        	
        	StringBuffer columnMask = new StringBuffer();
        	
        	for (i = 2;  i <= numberOfColumns; i++) {
        		columnNames.append(rsmd.getColumnName(i) + ", ");
        		columnMask.append("?, ");
			}
        	
        	columnNames.delete(columnNames.length() - 2 , columnNames.length());
        	columnMask.delete(columnMask.length() - 2 , columnMask.length());
        	
        	String query = "insert into " + tableName + " (" + columnNames + ") values (" + columnMask + ")";
        	logger.info(query);
            preparedStmt = con.prepareStatement(query);
            
            for (i = 1;  i < numberOfColumns; i++) {
            	String columnTypeName = rsmd.getColumnTypeName(i + 1);
            	logger.info(columnTypeName + " " + columnValue[i - 1]);
            	switch (columnTypeName) {
				case "VARCHAR":
					preparedStmt.setString(i, columnValue[i - 1]);
					break;
				case "INT":
					preparedStmt.setInt(i, Integer.parseInt(columnValue[i - 1]));
					break;
				case "TINYINT":
					preparedStmt.setShort(i, Byte.parseByte(columnValue[i - 1]));
					break;
				case "DATE":
					preparedStmt.setDate(i, Date.valueOf(columnValue[i - 1]));
					break;

				default:
					preparedStmt.setString(i, columnValue[i - 1]);
					break;
				}
            	
			}
            
            preparedStmt.execute();

         } catch (SQLException e)
		{
        	 logger.log(Level.SEVERE, "BotDAO.There are problems with the query " +"insert into " + tableName + " (" + columnNames + ") values (" + columnValue + ")", e);
             throw e;
         } finally {
        	 if(rs != null) rs.close();
             if(st != null) st.close();
        	 if(preparedStmt != null) preparedStmt.close();
         }
	}
	
	//this function check is user exist
		public Boolean isUserExist(String checkingLogin) throws SQLException{
			
			Boolean result = false;
			Statement st = null;
	        ResultSet rs = null;
	        String query = "select * from Users where Login = \'" + checkingLogin + "\'";
	        
			try
	        {
	            st = con.createStatement();
	            rs = st.executeQuery(query);
	            if (rs.next())
	            	result = true;
	         } 
			catch (SQLException e)
			{
	       	 logger.log(Level.SEVERE, "BotDAO.There are problems with the query " + query, e);
	            throw e;
	        } finally {
	       	 	if(rs != null) rs.close();
	            if(st != null) st.close();
	        }
			return result;
		}
	
	//this function check is command exist
	public Boolean isCommandExist(String checkingCommand) throws SQLException{
		
		Boolean result = false;
		Statement st = null;
        ResultSet rs = null;
        String query = "select * from Commands where name = \'" + checkingCommand + "\'";
        
		try
        {
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next())
            	result = true;
         } 
		catch (SQLException e)
		{
       	 logger.log(Level.SEVERE, "BotDAO.There are problems with the query " + query, e);
            throw e;
        } finally {
       	 	if(rs != null) rs.close();
            if(st != null) st.close();
        }
		return result;
	}
	
	//this function delete User
		public void deleteUser(String userLogin) throws SQLException{

			PreparedStatement preparedStmt = null;
			String query = "delete from Users where Login = ?";
			
			try
	        {
		      preparedStmt = con.prepareStatement(query);
		      preparedStmt.setString(1, userLogin);
		 
		      preparedStmt.execute();
	        } 
			catch (SQLException e)
			{
				logger.log(Level.SEVERE, "BotDAO.There are problems with the query " + query, e);
	            throw e;
	        } finally {
	       	 if(preparedStmt != null) preparedStmt.close();
	        }
		}
	
	//this function delete row from some table
	public void deleteRowFromTable(int delId, String tableName) throws SQLException{

		PreparedStatement preparedStmt = null;
		String query = "delete from " + tableName + " where id = ?";
		
		try
        {
	      preparedStmt = con.prepareStatement(query);
	      preparedStmt.setInt(1, delId);
	 
	      preparedStmt.execute();
        } 
		catch (SQLException e)
		{
			logger.log(Level.SEVERE, "BotDAO.There are problems with the query " + query, e);
            throw e;
        } finally {
       	 if(preparedStmt != null) preparedStmt.close();
        }
	}
}
