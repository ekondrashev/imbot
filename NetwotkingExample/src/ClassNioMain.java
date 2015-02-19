import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ClassNioMain {

	public static void main(String[] args) throws IOException {

		RandomAccessFile aFile = new RandomAccessFile("C:/Users/IT School/Galina/ClassJava/IOsrc.txt", "rw");
		RandomAccessFile aFileCopy = new RandomAccessFile("C:/Users/IT School/Galina/ClassJava/IOdsc.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		FileChannel outChannel = aFileCopy.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			System.out.println("Read " + bytesRead);
			buf.flip();

			while (buf.hasRemaining()) {
				bytesRead = outChannel.write(buf);
			}

			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();

	}
}
