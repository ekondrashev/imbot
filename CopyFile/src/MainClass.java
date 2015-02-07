import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class MainClass {
	
	private static Logger log = Logger.getLogger(MainClass.class.getName()); 

	public static void main(String[] args) throws IOException {
		System.out.println("Программа копирования файла");
		System.out.print("Select a file to copy : ");
		String source = selectPath();
		System.out.print("Select a destination path to copy : ");
		String dest = selectPath();

		fileCopyFiles(source, dest);

		if (fileIsEquals(source, dest)) {
			System.out.println("The file has been copied successfully");
		} else {
			System.out.println("Ups.... :-(");
		}
	}

	// Write path to a file. Return path to a file
	public static String selectPath() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		return reader.readLine(); // reading lines that the user entered
	}

	// Copy file by FileInputStream & FileOutputStream
	public static void fileCopyIOS(String source, String destination)
			throws IOException {

		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(destination);
			int copy;
			while ((copy = is.read()) != -1)
				os.write(copy);

		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
		}
	}

	// Copy file by FileReader & FileWriter
	public static void fileCopyFrw(String source, String destination)
			throws IOException {
		Reader reader = new FileReader(source);
		Writer writer = new FileWriter(destination);

		try {
			int data = reader.read();
			while (data != -1) {
				writer.write(data);
				data = reader.read();
			}
		} finally {
			if (reader != null)
				reader.close();
			if (writer != null)
				writer.close();
		}

	}
	
	// Copy file by Files
		public static void fileCopyFiles(String source, String destination) throws IOException{
			byte[] fileArray = Files.readAllBytes(Paths.get(source));
			Files.write(Paths.get(destination), fileArray);
		}

	// Compares 2 files. If files are equals return true, otherwise false
	public static boolean fileIsEquals(String source, String destination)
			throws IOException {
		BufferedReader reader1 = new BufferedReader(new FileReader(source));
		BufferedReader reader2 = new BufferedReader(new FileReader(destination));

		String line1 = null;
		String line2 = null;
		boolean flag = true;
		try {
			while (((line1 = reader1.readLine()) != null)
					&& ((line2 = reader2.readLine()) != null)) {
				if (!line1.equalsIgnoreCase(line2))
					flag = false;
			}
		} catch (IOException e) {
			log.info(e.getMessage());
		} finally {
			if (reader1 != null)
				reader1.close();
			if (reader2 != null)
				reader2.close();
		}

		return flag;

	}

}
