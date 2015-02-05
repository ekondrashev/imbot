import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainCopyFiles {
	public static void main(String[] args) throws IOException {

		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream(
					"C:/Users/IT School/Galina/ClassJava/IOsrc.txt");
			out = new FileOutputStream(
					"C:/Users/IT School/Galina/ClassJava/IOdsc.txt");
			int c;

			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
