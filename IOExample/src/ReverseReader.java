import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class ReverseReader extends FilterReader {

	protected ReverseReader(Reader in) throws IOException {
		super(in);
	}

	public char[] revers(char[] masiv) throws IOException {
		char[] masivRevers = new char[masiv.length];
		for (int i = 0, j = masiv.length - 1; i < masiv.length; i++) {
			masivRevers[i] = masiv[j--];
		}
		return masivRevers;
	}


	public int read(char[] arr, int off, int len) throws IOException {
		int i = 0;
		
		//i = in.read(arr, off, len);
		System.out.println(i);
		for (char c : arr)
			System.out.print(c);
		char[] arrReverce = revers(arr);

		//System.arraycopy(arrReverce,0,arr,0,arrReverce.length);
		//arr=arrReverce.clone();
		arr = Arrays.copyOf(arrReverce,arrReverce.length);
		for (char c : arr)
			System.out.print(c);
		return len;
	}
}

