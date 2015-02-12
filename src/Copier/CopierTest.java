package Copier;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class CopierTest {

	@Test
	public void oldStyleTest() throws IOException {
		Copier.fileCopyOldStyle("E:/text.txt", "E:/text1.txt");
		byte[] fileArray1 = Files.readAllBytes(Paths.get("E:/text.txt"));
		byte[] fileArray2 = Files.readAllBytes(Paths.get("E:/text1.txt"));
		assertArrayEquals(fileArray2, fileArray1);
	}

	@Test
	public void readerWriterTest() throws IOException {
		Copier.fileCopyReaderWriter("E:/text.txt", "E:/text2.txt");
		byte[] fileArray1 = Files.readAllBytes(Paths.get("E:/text.txt"));
		byte[] fileArray2 = Files.readAllBytes(Paths.get("E:/text2.txt"));
		assertArrayEquals(fileArray2, fileArray1);
	}

	@Test
	public void newStyleTest() throws IOException {
		Copier.fileCopyNewStyle("E:/text.txt", "E:/text3.txt");
		byte[] fileArray1 = Files.readAllBytes(Paths.get("E:/text.txt"));
		byte[] fileArray2 = Files.readAllBytes(Paths.get("E:/text3.txt"));
		assertArrayEquals(fileArray2, fileArray1);
	}

}