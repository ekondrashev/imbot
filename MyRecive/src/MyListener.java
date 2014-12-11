import java.util.Map;


public class MyListener {

	
	@SuppressWarnings("static-access")
	public static void main(String[] args)  {
		try{
		ParsingRegulary pars=new ParsingRegulary();
		Map<String,String> map= pars.decodeToMap(args);
		
		String host="localhost";
		String queue="QUEUE";
		if (map.containsKey("command") && map.containsValue("start")){
			if (map.containsKey("host")) 
				host=map.get("host");
			if (map.containsKey("queue")) 
				queue=map.get("queue");
		}
		
		Recv rec=new Recv(queue,host);
		rec.recive();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
