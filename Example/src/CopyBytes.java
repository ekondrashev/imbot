import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyBytes {
	public static void main(String[] args) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try{
			in = new FileInputStream("File.txt");
			out = new FileOutputStream("NewFile.txt");
			int g;
			
			while ((g=in.read()) != -1){
				out.write(g);
			}
		} finally {
			if(in != null) {
				in.close();
			}
			if(out != null) {
				out.close();
			}
			}
	}
}
	