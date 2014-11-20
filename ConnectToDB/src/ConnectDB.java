import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


public class ConnectDB {
	@SuppressWarnings("rawtypes")
	
	public static void main(String[] args) {
		
		Map<String, String> list =new LinkedHashMap<String, String>();//структура нашей таблицы пока метод получения из базы не написал, пишем вручную
		
		list.put("Id", "int");
		list.put("Login", "varchar(80)");
		list.put("FirstName", "varchar(180)");
		list.put("LastName", "varchar(180)");
		
		
		Table our=new Table(list);
		
		for (Entry<String, Type> elem : our.getTableStructure().entrySet()) 
		{
			System.out.println("key:"+elem.getKey()+" value:"+elem.getValue().getVariable());
		}	
		
	}

}
