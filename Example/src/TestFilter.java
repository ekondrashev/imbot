import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;


public class TestFilter {

	@Test
	public void testReferseFilter() throws IOException {
		String input = "this is 123\n" +
	                   "multi line string\n" +
				       "test it plz";
		Reader cr = new CharArrayReader(input.toCharArray());
		
		Reader r = new ReverseReader(cr);
		BufferedReader br = new BufferedReader(r);
		String a = br.readLine();
		String b= "";
		for(int i=a.length()-1; i>=0; i--) {
			b = b + a.charAt(i); 
			System.out.println(a.charAt(i));
		}
		assertEquals(br.readLine(), "zlp ti tset");
		assertEquals(br.readLine(), "gnirts enil itlum");
		assertEquals(br.readLine(), "321 si siht");
		System.out.println("br");
	}

}
