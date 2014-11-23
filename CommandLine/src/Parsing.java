import java.util.Map;


public interface Parsing {
	
	public static Map<String, String> decodeToMapString(String args[]) {
		return Exec.decodeToMap(args, 1);
	}
	
	public static Map<String, String> decodeToMapPattern(String args[]) {
		return Exec.decodeToMap(args, 0);
	}
	
}
