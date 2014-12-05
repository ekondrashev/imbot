import java.util.LinkedHashMap;
import java.util.Map;
 
 
public class Exec {
 
       
        public static Map<String, String> myParsingMethod(String[] args) {
                Map<String, String> ourResult = new LinkedHashMap<>();
                for (int i=0; i< args.length-1; i=i+1){
                        System.out.println(args[i]);
                String k=args[i].substring(2,6);}
               
            return  ourResult;
               
        }
 
}