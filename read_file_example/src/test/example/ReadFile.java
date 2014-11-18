package test.example;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String readFile(String path) {
		String result = null;
		String warn = null;
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		try {
			reader = new FileReader(path);
			try 
			{
				bufferedReader = new BufferedReader(reader);
				String line = bufferedReader.readLine();
				result = line;
				if (bufferedReader != null)
					{
						bufferedReader.close();
					}
			} 
			catch (IOException ioe) 
			{
				// unable to read
				result = null;
			} 
			reader.close();			
			} catch (FileNotFoundException e) {
				// 
				result = null;
				warn = "File not found";
			} catch (IOException ioe) {
				// unable to close or whatever else
				//result = null; 
				warn = "Unable to close";
			} 
			finally {
				// do something
				System.out.println("Will print this anyway!");
				if (warn != null) {
				System.out.println(warn);
				} else {
					System.out.println("No warnings");
				}
			}
		return result;
	}

}
