import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParsingRegulary implements Parsing{

	private static final String USAGE = "Usage: Exec --cmd=<os_cmd> -param1=<value> -param2=<value>";
	
	private static Matcher controlMyString(String pat, String forFind) 
	{
		Pattern patt = Pattern.compile(pat);
		Matcher mat = patt.matcher(forFind);
		return mat;
	}
	
	public static Map<String, String> decodeToMapPattern(String args[]) {
		Map<String, String> ourResult = new LinkedHashMap<>();

		String key = null, value = null;
		String errorkey = "Error";
		String errorvalue = "Error input command!!!";


			if (args.length > 0) {
				String myPatternCmd = "--[Cc][Mm][Dd]=.+$";
				String myPatternArg = "=.+$";
				String myPatternHelp = "--[Hh][Ee][Ll][Pp]$";
				Matcher m;
				
				if (controlMyString(myPatternHelp,args[0]).matches())
				{
					ourResult.put("help", USAGE);
					return ourResult;
				}	
				else if (controlMyString(myPatternCmd,args[0]).matches())

				{
					m = controlMyString(myPatternArg, args[0]);
					if (m.find())
					{
						key = "command";
						ourResult.put(key, m.group().replaceAll("=", ""));
					}
					
				}
				else
					{
					ourResult.put(errorkey, errorvalue);
					ourResult.put("help", USAGE);
					return ourResult;
					}
				
				
				String myPatternCmdVerify = "-[a-zA-Z_]+=.+$";
				myPatternCmd = "-[a-zA-Z_]+=";				
				myPatternArg = "=.+$";
				
				
				for (int i = 1; i < args.length; i++)
				{
					String arg = args[i];
					
					m = controlMyString(myPatternCmdVerify, arg);
					
					if (m.matches()) {
						m = controlMyString(myPatternCmd, arg);
						key=(m.find()?m.group():errorkey);
						
						m = controlMyString(myPatternArg, arg);
						value=(m.find()?m.group():errorkey);
						
						key=key.replaceAll("-", "").replaceAll("=", "").toLowerCase();
						value=value.replaceAll("=", "").toLowerCase();
						
						ourResult.put(key, value);
						
						if (key==errorkey) break; 
					}
				}
			}
			else ourResult.put("help", USAGE);

			return ourResult;
		}

	@Override
	public Map<String, String> decodeToMap(String[] args) {
		return decodeToMapPattern(args);
		
	}
	}


