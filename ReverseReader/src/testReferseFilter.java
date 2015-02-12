import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;


public class testReferseFilter {

	@Test
	public void test() throws IOException {
		String input = "this is 123\n" + 
						"multi line string\n" + 
						"test it pls";
		Reader cr = new CharArrayReader(input.toCharArray());
		
		Reader r = new ReverseReader(cr);
		BufferedReader br = new BufferedReader(r);
		assertEquals(br.readLine(), "zlp ti tset");
		assertEquals(br.readLine(), "gnirts enil itlum");
		assertEquals(br.readLine(), "321 si siht");
	}

}
