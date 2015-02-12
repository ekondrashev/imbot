package Copier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class Copier {

	public static void main(String[] args) throws IOException {
		System.out.println("Программа копирования файла");
		System.out.print("Select a file to copy : ");
		String source = selectPath();
		System.out.print("Select a destination path to copy : ");
		String destination = selectPath();

		fileCopyOldStyle(source, destination);
        fileCopyReaderWriter(source, destination);
		fileCopyNewStyle(source, destination);

	}

	// Write path to a file. Return path to a file
	public static String selectPath() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		return reader.readLine(); // reading lines that the user entered
	}

	// Copy "old style"
	public static void fileCopyOldStyle(String source, String destination) throws IOException {
		FileInputStream is = new FileInputStream(source);
		try {
			FileOutputStream os = new FileOutputStream(destination);
			try {
				byte[] buffer = new byte[4096];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);

				}
			} finally {
				os.close();
			}
		} finally {
			is.close();
		}
		System.out.println("oLd style, baby");
	}
	// copy by reader/writer
	public static void fileCopyReaderWriter(String source, String destination) throws IOException  {
		BufferedReader myReader = null;
		BufferedWriter myWriter = null;
 
		try {
			myReader = new BufferedReader(new FileReader(source));
			myWriter = new BufferedWriter(new FileWriter(destination));
 
			String line;
			while ((line = myReader.readLine()) != null) {
				myWriter.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			myReader.close();
			myWriter.close();
		}
		System.out.println("get read or write, baby");
	}

	// Copy "new style"
	public static void fileCopyNewStyle(String source, String destination) {
		Path pathSource = Paths.get(source);
		Path pathDestination = Paths.get(destination);
		try {
			Files.copy(pathSource, pathDestination,
					StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("NeW style, baby");
		
	}

}
