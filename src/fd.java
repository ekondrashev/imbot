
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class fd {

 private static final String USAGE = "Usage: Exec --cmd=<os_cmd> -param1=<value> -param2=<value>";

 public static Map<String, String> DecodeToMap(String args[]) {	
Map<String, String> AllResult = new HashMap<>();

 String cmdSignature = "--cmd=";
 String key,value;
//check the validity of cmdSignatur
 /*if the string is not empty then the following expression 
  * check the validity of cmdSignatur.
  * our first item in the list is assigned to the key <command>,
  * and value is assigned to all that is in the argument of the end of the key to space 
  * */
 if (args.length>0){
 if (args[0].startsWith(cmdSignature)) {
 value=args[0].substring(cmdSignature.length(),args[0].length());
 key="command";
AllResult.put(key, value);
 }
 }

 //checking for "-" "="
 /*
  * then checks the arguments of such characters as the " -" and " =" .
  *  "-" Should is always at the beginning of the line , that is, the index must be equal to "0"
  *  "=" index must be greater than "0"
  *  if the conditions are not met , the key receives an "error", value takes the "Error input command!!!"
  *  and exit the loop.
  *  another "key and value" takes appropriate values
  *  exit the method
  * */
 for (int i=1; i<=args.length-1 ;i++) {
 String arg=args[i];

int posMinus=arg.indexOf("-");
 int posEquals=arg.indexOf("=");

 if (posEquals <= posMinus+1 || posMinus != 0) {
	 
 value="command empty!!!";
 key="Error";
 AllResult.put(key, value);
 break;
 }

 key=arg.substring(posMinus+1,posEquals);
 value=arg.substring(posEquals+1,arg.length());
 AllResult.put(key, value);
 }
 return AllResult;
 }
// method Main
 /*then the output of all of our results
  * 
  * 
  * 
  * */
 public static void main(String args[]) {

 Map<String, String> AllResult1 = DecodeToMap(args);

 if (AllResult1.size() == 0) {
 System.out.println(USAGE);

}
 else if (AllResult1.containsKey("Error")) {

 System.out.println(AllResult1.get("Error"));
 }
 else {
 System.out.println("command"+":"+AllResult1.get("command"));

 for (Entry<String, String> elem : AllResult1.entrySet()) {

 if (elem.getKey()=="command")
 continue;

 System.out.println(elem.getKey() +":" + elem.getValue());
 }
 }

 }


}