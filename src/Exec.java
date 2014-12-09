import java.util.LinkedHashMap;
import java.util.Map;

public class Exec {

	public static Map<String, String> myParsingMethod(String[] args) {
		Map<String, String> ourResult = new LinkedHashMap<>();

		for (int i = 0; i <= args.length - 1; i++) {
			System.out.println(args[i]);
			int posEquals = args[i].indexOf("=");
			if (posEquals <= +1) {
				String errorvalue = "Error command !!!";
				String errorkey = "Error";
				ourResult.put(errorkey, errorvalue);
				System.out.println(ourResult);
				return ourResult;
			} else if (posEquals > 1) {
				String key = args[i].substring(2, posEquals);
				String value = args[i].substring(posEquals + 1);
				ourResult.put(key, value);
				System.out.println(ourResult);
			}
		}

		return ourResult;

	}

}