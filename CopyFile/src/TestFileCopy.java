import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestFileCopy {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		MainClass.fileCopyIOS("C:/text.txt", "C:/text1.txt");
		byte[] fileArray1 = Files.readAllBytes(Paths.get("C:/text.txt"));
		byte[] fileArray2 = Files.readAllBytes(Paths.get("C:/text1.txt"));
		assertArrayEquals(fileArray2, fileArray1);
	}
	
	@Test
	public void test1() throws IOException {
		MainClass.fileCopyFrw("C:/text.txt", "C:/text2.txt");
		byte[] fileArray1 = Files.readAllBytes(Paths.get("C:/text.txt"));
		byte[] fileArray2 = Files.readAllBytes(Paths.get("C:/text2.txt"));
		assertArrayEquals(fileArray2, fileArray1);
	}
	
	@Test
	public void test2() throws IOException {
		MainClass.fileCopyFiles("C:/text.txt", "C:/text3.txt");
		byte[] fileArray1 = Files.readAllBytes(Paths.get("C:/text.txt"));
		byte[] fileArray2 = Files.readAllBytes(Paths.get("C:/text3.txt"));
		assertArrayEquals(fileArray2, fileArray1);
	}

}
