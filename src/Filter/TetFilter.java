package Filter;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;

public class TetFilter {
	@Test
public void testRefersFiilter() throws IOException{
		String input = "this is 123\n" +
                       "multi line string\n" +
                       "test it plz"	;
		Reader cr = new CharArrayReader(input.toCharArray());
		
		Reader r = new ReverseReader(cr);
		BufferedReader br = new BufferedReader(r);
		assertEquals(br.readLine(), "this is 123");
		String resultRead = br.readLine();
		for(int i=0;i<resultRead.length();i++){
			System.out.println(resultRead.charAt(i));
			
			
		
		}
		
//		assertEquals(br.readLine(), "zlp ti tset");
//		assertEquals(br.readLine(), "gnirts enil itlum");
//		assertEquals(br.readLine(), "321 si siht");
//		

	}
}
