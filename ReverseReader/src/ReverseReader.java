import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;


public class ReverseReader extends FilterReader{
	
	private static Logger log = Logger.getLogger(ReverseReader.class.getName());

	protected StringBuilder str = new StringBuilder();
	protected int pos = 0;
	
	protected ReverseReader(Reader in) throws IOException {
		super(in);
	}
	
	boolean intag = false;
	
	 public int read(char[] buf, int offset, int count) throws IOException {
		 
		int numchars = 0; 
	    char[] t = new char[buf.length];
	    while (numchars == 0) {
	      numchars = in.read(buf, offset, count); 
	      if (numchars == -1)
	        return -1;
	    } 
	    log.info(String.valueOf(numchars));
	    for (int i = 0, j = numchars - 1; i < numchars; i++, j--) {
			   t[i] = buf[j];
		}
	    for (int i = 0; i < numchars; i++) {
			buf[i] = t[i];
		}
	    log.info(String.valueOf(buf));
	    return numchars; // Then return
	    }
	 
}
