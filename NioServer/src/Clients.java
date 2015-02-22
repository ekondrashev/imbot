
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;


public class Clients implements Runnable {
    private LinkedList<SocketChannel> chanals;

    

    public Clients(LinkedList<SocketChannel> chanals) {
	super();
	this.chanals = chanals;
    }
    
    @Override
    public void run() {
	while (true) {
	    for (SocketChannel socketChannel : chanals) {
		ByteBuffer buffer = ByteBuffer.wrap("Hello".getBytes());
		try {
		    socketChannel.write(buffer);
		} catch (IOException e) {
		    e.printStackTrace();
		    chanals.remove(socketChannel);
		}
	    }
	}
    }

}
