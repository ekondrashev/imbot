import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;


public class ReverseReader extends FilterReader{

	protected StringBuilder str = new StringBuilder();
	protected ReverseReader(Reader in) throws IOException {
		super(in);
		int i;
		while ((i = in.read()) != -1){
			str.append(i);
		}
	}
	
	public int read(){
		return 0;
	}
}
