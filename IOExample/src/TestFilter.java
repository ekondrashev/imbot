import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;


public class TestFilter {

	@Test
	public void testReferseFilter() throws IOException {
		String input="qwer kl 123\n+" +
				"poiu 456\n+" +
				"hjk 678\n";
		Reader cr=new CharArrayReader(input.toCharArray());
		Reader r=new ReverseReader(cr);

		BufferedReader br=new BufferedReader(r);
		assertEquals(br.readLine(),"876 kjh");
		assertEquals(br.readLine(),"654 uiop");
		assertEquals(br.readLine(),"321 lk rewq");
		br.close();
	}

}
