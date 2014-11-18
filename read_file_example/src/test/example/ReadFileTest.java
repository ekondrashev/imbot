package test.example;
import junit.framework.TestCase;


public class ReadFileTest extends TestCase {

	public void testReadfile() {
		//Remember to create a file on file system
		String path = "C:/1.txt";
		String result = ReadFile.readFile(path);
		
		this.assertEquals(result, "example output");
	}
}
