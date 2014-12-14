package BD;

import java.sql.Connection;
import java.sql.DriverManager;
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
			st.executeUpdate("insert into Persons (PersonID, LastName, FirstName, Address, City) value (1980,'Gulov', 'Yura', 'no address', 'Odessa')");
			st.executeUpdate("insert into Persons (PersonID, LastName, FirstName, Address, City) value (2014,'Petrov', 'Vasya', 'no address', 'Odessa')");
			
			rs = st.executeQuery("select * from Persons");
			
			while (rs.next())
		      {
		        int id = rs.getInt("PersonID");
		        String lastName = rs.getString("LastName");
		        String firstName = rs.getString("FirstName");
		        String address = rs.getString("Address");
		        String city = rs.getString("City");
		         
		        // print the results
		        System.out.format("%s, %s, %s, %s, %s\n", id, firstName, lastName, address, city);
		      }
		      st.close();
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
