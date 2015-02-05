import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;


public class ComparisonContentTest {

	@SuppressWarnings("resource")
	@Test
	public void test() throws IOException {
		MainCopyFiles.main(null);
		BufferedReader bufferedReaderSrc,bufferedReaderDst;
		FileReader readerSrc,readerDsc;
		
		String pathSrc = "C:/Users/IT School/Galina/ClassJava/IOsrc.txt";
		String pathDst = "C:/Users/IT School/Galina/ClassJava/IOdsc.txt";
		
		
		readerSrc=new FileReader(pathSrc);
		readerDsc=new FileReader(pathDst);
		
		bufferedReaderSrc = new BufferedReader(readerSrc);
		bufferedReaderDst = new BufferedReader(readerDsc);
		
		assertEquals(bufferedReaderSrc.readLine(), bufferedReaderDst.readLine());
	}

}
