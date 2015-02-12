import java.io.FilterReader;
import java.io.Reader;


public class ReverseReader extends FilterReader  {
     public ReverseReader(Reader in) {
    	 super (in);
    	 
     }
}
