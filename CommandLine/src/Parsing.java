import java.util.Map;


public interface Parsing {
	
	public Map<String, String> decodeToMapString(String[] args);
	
	public Map<String, String> decodeToMapPattern(String[] args);
	
}
