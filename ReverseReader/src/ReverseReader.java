import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;


public class ReverseReader extends FilterReader{

	protected ReverseReader(Reader in) throws IOException {
		super(in);
		
	}
}
