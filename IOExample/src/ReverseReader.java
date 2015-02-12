import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class ReverseReader extends FilterReader {

	protected ReverseReader(Reader in) throws IOException {
		super(in);

		//int c;
		//while ((c = in.read()) != -1)
		//	System.out.print((char) c);

		BufferedReader br = new BufferedReader(in);
	    revers(br.readLine().toCharArray());
	    revers(br.readLine().toCharArray());
	    revers(br.readLine().toCharArray());
	}

	public void revers(char[] masiv) {
		char[] masivRevers = new char[masiv.length];
		System.out.println("\nСтало так:");
		for (int i = 0, j = masiv.length - 1; i < masiv.length; i++) {
			masivRevers[i] = masiv[j--];
			System.out.println("masivRevers[" + i + "] = " + masivRevers[i]);
		}
	}
	public int read(char[] arr, int off, int len){
		return len;
		
	}
}
