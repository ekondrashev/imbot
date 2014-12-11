import java.util.LinkedHashMap; 
import java.util.Map;
 
 public class ParsingString implements Parsing{ 
 
 	private static final String USAGE = "Usage: --cmd=<os_cmd> -param1=<value> -param2=<value>"; 
 	 
 	public static Map<String, String> decodeToMapString(String args[]) { 
 		Map<String, String> ourResult = new LinkedHashMap<>(); 
 
 		String cmdSignature = "--cmd="; 
 		String cmdHelp = "--help"; 
 		String key = null, value = null; 
 		String errorkey = "Error"; 
 		String errorvalue = "Error input command!!!"; 
 
 			if (args.length > 0) { 
 				if (args[0].startsWith(cmdSignature)) { 
 					value = args[0].substring(cmdSignature.length(), 
 							args[0].length()); 
 					key = "command"; 
 					ourResult.put(key, value); 
 				} 
 
 
 				else if (args[0].startsWith(cmdHelp)) { 
 					value = USAGE; 
 					key = "help"; 
					ourResult.put(key, value); 
 					return ourResult; 
 				} else { 
 					ourResult.put(errorkey, errorvalue); 
 					return ourResult; 
 				} 
 
 
 			} 
 
 
 			for (int i = 1; i <= args.length - 1; i++) { 
 				String arg = args[i]; 
 
 
 				int posMinus = arg.indexOf("-"); 
 				int posEquals = arg.indexOf("="); 
 
 
 				if (posMinus == -1 || posEquals == -1) { 
 					ourResult.put(errorkey, errorvalue); 
 					break; 
 				} 
 
 
 				key = arg.substring(posMinus + 1, posEquals); 
 				value = arg.substring(posEquals + 1, arg.length()); 
 				ourResult.put(key, value); 
 			} 
 			return ourResult; 
 		} 
 	 
 
 	@Override 
 	public Map<String, String> decodeToMap(String[] args) { 
 		return decodeToMapString(args); 
 	} 
 
 	 
 } 
