import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;



public class MyWorkDB {
	@SuppressWarnings({ "rawtypes" })
	
	public static void main(String[] args) throws SQLException, Exception {
		try{
		String userName = "imbot";
		String password = "P@ssw0rd";
		String url = "jdbc:mysql://217.146.253.39/imbot"; 
		
		ConnectDB ourConn=new ConnectDB(url,userName,password); //create class connectDB
		ourConn.getConnectToDB();//create connect 
		
	    ResultSet rs = Query.openQuery(ourConn.conn,"show columns from APIName"); //return table structure
	 
	    Map<String, String> list =new LinkedHashMap<String, String>(); 
	    
	    while (rs.next()) //list all fields except auto_increment
	    {
	    	//if (rs.getString("Extra").toString().contains("auto_increment")) ;
	    
	    	list.put(rs.getString("Field"), rs.getString("Type"));
	    	//System.out.println(rs.getString("Field")+":"+rs.getString("Type"));
	    	//System.out.println(rs.);
	    }
	    
	     Table our=new Table(list); //create our mutant Map
//		
//	     //for example find row in table by id
//	     
	     our=Query.returnRecordByID(ourConn.conn, 1, "APIName",our);
	     
			for (Entry<String, Type> elem : our.getTableStructure().entrySet()) //output values from our structure 
			{
				System.out.println("key:"+elem.getKey()+" value:"+elem.getValue().getVariable());//+" type: "+elem.getValue().tellType()
			}	
		
//	     our.getTableStructure().get("PersonID").setVariable(1);
//	     our.getTableStructure().get("LastName").setVariable("Potemkin");
//	     our.getTableStructure().get("FirstName").setVariable("Alex");
//	     our.getTableStructure().get("Address").setVariable("Starickogo 20/1");
//	     our.getTableStructure().get("City").setVariable("Odessa");
//	    
//			for (Entry<String, Type> elem : our.getTableStructure().entrySet()) //output values from our structure 
//			{
//				System.out.println("key:"+elem.getKey()+" value:"+elem.getValue().getVariable());//+" type: "+elem.getValue().tellType()
//			}
//	
//	     Query.insertToDBfromMap(ourConn.conn,our,"Persons");
	 	ourConn.closeConnection();
	     
		 
	     
		}catch (SQLException e) {
		    System.out.println(e.getMessage());
		}
		
		catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		
		
	}

}