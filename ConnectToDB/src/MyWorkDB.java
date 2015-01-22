import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.text.SimpleDateFormat;



public class MyWorkDB {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws SQLException, Exception {
		try{
			
//		String userName = "imbot";
//		String password = "P@ssw0rd";
//		String url = "jdbc:mysql://217.146.253.39/imbot"; 
		
        String userName = "root";
		String password = "111111";
		String url = "jdbc:mysql://localhost/imbot"; 
                    
		ConnectDB ourConn=new ConnectDB(url,userName,password); //create class connectDB
		Connection conn=ourConn.openConnect();
	    ResultSet rs = Query.openQuery(conn ,"show columns from users"); //return table structure
	 
	    Map<String, String> list =new LinkedHashMap<String, String>(); 
	    
	    while (rs.next()) 
	    {
	    	list.put(rs.getString("Field"), rs.getString("Type"));
	    }
	    
	    
	    Table our=new Table(list); //create our mutant Map
		
//	     //for example find row in table by id
	     
	     our=Query.returnRecordByID(conn, 1, "users",our);
	     
			for (Entry<String, Type> elem : our.getTableStructure().entrySet()) //output values from our structure 
			{
				System.out.println("key:"+elem.getKey()+" value:"+elem.getValue().getVariable());//+" type: "+elem.getValue().tellType()
			}	
		
			
			our.getTableStructure().get("LastName").setVariable("Potemkin");
			our.getTableStructure().get("FirstName").setVariable("Alex");
			our.getTableStructure().get("MiddleName").setVariable("Yurievich");
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			Date date = formatter.parse("22.05.1981");
			our.getTableStructure().get("DateOfBirth").setVariable(date);
			our.getTableStructure().get("Login").setVariable("_HakerAlex_");
   
			for (Entry<String, Type> elem : our.getTableStructure().entrySet()) //output values from our structure 
			{
				System.out.println("key:"+elem.getKey()+" value:"+elem.getValue().getVariable());//+" type: "+elem.getValue().tellType()
			}
//	
	   if (!Query.controlUniqueRecordByField(conn, "Login", "users", our)) Query.insertToDBfromMap(conn,our,"users");
	   else System.out.println("It's not unique login");;
			
		ourConn.closeConnection(conn);
	     	 
	     
		}catch (SQLException e) {
		    System.out.println(e.getMessage());
		}
		
		catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		
		
	}

}