import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Parser {

	public static void main1 (String args[]) {

Map<String, String> m=Sneight.DecodeToMap(args);	



for (int i=1; i<=args.length-1 ;i++) {
String arg=args[i];

int posMinus=arg.indexOf("--");
int posEquals=arg.indexOf("=");

if (posMinus!=0 || posEquals<=1) {
value="Error command!!!";
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
