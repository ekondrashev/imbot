package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BDExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  user = "imbot";
		String  password = "P@ssw0rd";
		
		String url = "jdbc:mysql://217.146.253.39/imbot";
		
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
			System.out.println("Driver loading success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection c = null;
		try{
			c = DriverManager.getConnection(url, user, password);
			System.out.println(c);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("show tables");
			while(rs.next()){
				System.out.println(rs.getString("tables_in_imbot"));
			}
			int PersonID = 1980;
	        String lastName = "Gulov";
	        String firstName = "Yura";
	        String address = "no address";
	        String city = "Odessa";
	        
	        String query = "insert into Persons (PersonID, LastName, FirstName, Address, City) values (?, ?, ?, ?, ?)";
	        PreparedStatement preparedStmt = c.prepareStatement(query);
	        
	        preparedStmt.setInt(1, PersonID);
	        preparedStmt.setString(2, lastName);
	        preparedStmt.setString(3, firstName);
	        preparedStmt.setString(4, address);
	        preparedStmt.setString(5, city);
	        preparedStmt.execute();
	        
			
			rs = st.executeQuery("select * from Persons");
			
			while (rs.next())
		      {
		        int id1 = rs.getInt("PersonID");
		        String lastName1 = rs.getString("LastName");
		        String firstName1 = rs.getString("FirstName");
		        String address1 = rs.getString("Address");
		        String city1 = rs.getString("City");
		         
		        // print the results
		        System.out.format("%s, %s, %s, %s, %s\n", id1, firstName1, lastName1, address1, city1);
		      }
		      st.close();
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
