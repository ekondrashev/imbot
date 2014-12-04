
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Sneight {

 private static final String USAGE = "Usage: --cmd=start --cmd=stop --cmd=sent_message";

 public static Map<String, String> DecodeToMap(String args[]) {	
Map<String, String> OurResult = new HashMap<>();

 String cmdSignature = "--cmd=";
 String key,value;

 if (args.length>0){
 if (args[0].startsWith(cmdSignature)) {
 value=args[0].substring(cmdSignature.length(),args[0].length());
 key="cmd";
 OurResult.put(key, value);
 }
	
 }

 for (int i=1; i<=args.length-1 ;i++) {
 String arg=args[i];
 
int posMinus=arg.indexOf("-");
 int posEquals=arg.indexOf("=");

 if (posMinus!=0 || posEquals<=+1) {
 value="Error command !!!";
 key="Error";
 OurResult.put(key, value);
 break;
 }

 key=arg.substring(posMinus+1,posEquals);
 value=arg.substring(posEquals+1,arg.length());
 OurResult.put(key, value);
 }
 return OurResult;
 }

 public static void main(String args[]) {

 Map<String, String> OurResult = DecodeToMap(args);

 if (OurResult.size() == 0) {
 System.out.println(USAGE);

}
 else if (OurResult.containsKey("Error")) {

 System.out.println(OurResult.get("Error"));
 }
 else {
 System.out.println("cmd"+":"+OurResult.get("cmd"));

 for (Entry<String, String> elem : OurResult.entrySet()) {

 if (elem.getKey()=="cmd")
 continue;

 System.out.println(elem.getKey() +":" + elem.getValue());
 }
 }

 }


}