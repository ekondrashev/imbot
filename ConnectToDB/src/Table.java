import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Table {

	@SuppressWarnings("rawtypes")
	private Map<String,Type> myStructureTable;

	
	Table (Map<String,String> structureTableMySql) 
	{ 
		initializing(structureTableMySql);
	}
	
	
	@SuppressWarnings("rawtypes")
	public  Type getTypeJavaFromTypeMySQL(String myTypeSQL)
	{
		Type javaType = null; //default
		
		if (myTypeSQL.startsWith("int")) 
			javaType=new Type<Integer>(0);
		else if (myTypeSQL.startsWith("varchar") || myTypeSQL.startsWith("char"))  
			javaType=new Type<String>("");
		else if (myTypeSQL.startsWith("date"))
			javaType=new Type<Date>();
		else if (myTypeSQL.startsWith("float") || myTypeSQL.startsWith("real"))
			javaType=new Type<Double>();
		return javaType;
	}

	
	@SuppressWarnings("rawtypes")
	public void initializing(Map<String,String> structureField){
		
	    myStructureTable=new LinkedHashMap<String, Type>();
	    	
		for (Entry<String, String> elem : structureField.entrySet()) 
		{
			this.myStructureTable.put(elem.getKey(),getTypeJavaFromTypeMySQL(elem.getValue()));
		}	
		
	}
	
	
	@SuppressWarnings("rawtypes")
	public Map<String,Type> getTableStructure(){
		
		return this.myStructureTable;
		
	}
	
	
}




