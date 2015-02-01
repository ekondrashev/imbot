import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.logging.Logger;









import org.json.JSONArray;
import org.json.JSONObject;



public class Transport implements Callable{
	
	private static final Logger logger = Logger.getLogger(Transport.class.getName());
	
	//start and finish points
	public String from = null, to = null; //points of route
	
	JSONObject json;
	
	//constructors
	public Transport(){};
	public Transport(String query){
		newQuery(query);
	}
	
	//query processing
	public void newQuery(String query){
		parseQuery(query);
		try {
			getJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//parse query string, gets start and finish points
	private void parseQuery(String query){
		int a = query.indexOf("from=");
		int b = query.indexOf("to=");
		if(a != -1 && b != -1){
			from = query.substring(a + 5, b);
			to	= query.substring(b + 3);
		}else{
			logger.info("Has not been found keywords \"from=\" or \"to=\"");
		}
	}
	
	//get the query result in JSON format
	private void getJson() throws IOException{
		BufferedReader reader = null;
		try {
			URL url = new URL("http://maps.googleapis.com/maps/api/directions/json?"
					+ "origin=" + URLEncoder.encode(from, "UTF-8") +"&"
					+ "destination=" + URLEncoder.encode(to, "UTF-8") +"&sensor=false&"
					+ "departure_time=" + (new Date().getTime()/1000) + "&mode=transit&region=ua&language=ru&alternatives=true");
			URLConnection conn = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuffer strCode = new StringBuffer("");
			int cp;
			while((cp = reader.read()) != -1){
				
				strCode.append((char)cp);
			}
			
			json = new JSONObject(strCode.toString());
			
			//System.out.println(strCode);
			PrintWriter out = new PrintWriter("c:\\textx.txt");
			 
	        try {
	            //Записываем текст у файл
	            out.print(strCode.toString());
	        } finally {
	            //После чего мы должны закрыть файл
	            //Иначе файл не запишется
	            out.close();
	        }
			
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			reader.close();
		}
	}
	
	//get result of query in string value
	public String getTransport(){
		logger.info(json.getString("status"));
		if(json.getString("status").equals("OK")){				//check the status of implementation query 
			System.out.println("Found the following routes:");
		}else{
			return "?? :-( ??";									//if some problem then return string 
		}
		StringBuffer str = new StringBuffer();
		
		JSONArray  routesArray = json.getJSONArray("routes");							// Array of routes
		for(int i = 0; i < routesArray.length(); i++){
			str.append("\nВариант " + (i+1) + ":\n");
			JSONArray legsArray = routesArray.getJSONObject(i).getJSONArray("legs");	//Array of route parameters
			for(int j = 0; j < legsArray.length(); j++){
				str.append("Расстояние от ");
				str.append(legsArray.getJSONObject(j).getString("start_address"));
				str.append("\nдо ");
				str.append(legsArray.getJSONObject(j).getString("end_address"));
				str.append(" : ");
				str.append(legsArray.getJSONObject(j).getJSONObject("distance").getString("text"));
				str.append(".\n");
				JSONArray stepsArray = legsArray.getJSONObject(j).getJSONArray("steps");
				for (int k = 0; k < stepsArray.length(); k++) {								//Steps of route
					if(stepsArray.getJSONObject(k).getString("travel_mode").equals("WALKING")){
						str.append(stepsArray.getJSONObject(k).getString("html_instructions"));
						str.append(".\n");
					}
					if(stepsArray.getJSONObject(k).getString("travel_mode").equals("TRANSIT")){
						JSONObject transitDetails = stepsArray.getJSONObject(k).getJSONObject("transit_details");
						str.append("Вам необходимо сесть на ");
						str.append(transitDetails.getJSONObject("line").getJSONObject("vehicle").getString("name"));
						str.append(" № ");
						if(transitDetails.getJSONObject("line").has("short_name") == true)
							str.append(transitDetails.getJSONObject("line").getString("short_name"));
						str.append(" (");
						if(transitDetails.getJSONObject("line").has("name") == true)
							str.append(transitDetails.getJSONObject("line").getString("name"));
						str.append(") и проехать от остановки \"");
						str.append(transitDetails.getJSONObject("departure_stop").getString("name"));
						str.append("\" до остановки \"");
						str.append(transitDetails.getJSONObject("arrival_stop").getString("name"));
						str.append("\". \n");
						str.append("Время отправления: ");
						str.append(transitDetails.getJSONObject("departure_time").getString("text"));
						str.append(" Время прибытия: ");
						str.append(transitDetails.getJSONObject("arrival_time").getString("text"));
						str.append(".\n");
					}
				}
			}
		}
		return str.toString();
	}
	
	public String call(){
		return getTransport();
	}
}
