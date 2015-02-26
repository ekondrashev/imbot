import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Set;

public class TrainingServer implements Runnable {
    private int port;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public TrainingServer(int port) {
	this.port = port;
    }

    @Override
    public void run() {
	try (ServerSocketChannel ssch = ServerSocketChannel.open();
		Selector selector = Selector.open();) {
	    
	    ssch.socket().bind(new InetSocketAddress(port));
	    ssch.configureBlocking(false);
	    ssch.register(selector, SelectionKey.OP_ACCEPT);
	    System.out
		    .println("Server is running and waits for a connection .");
	    while (!Thread.currentThread().isInterrupted()) {
		int readyChannels = selector.select(1000);
		if (readyChannels == 0)
		    continue;
		Set<SelectionKey> selectedKeys = selector.selectedKeys();
		Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

		while (keyIterator.hasNext()) {
		    SelectionKey key = keyIterator.next();
		    keyIterator.remove();
		    
		    if (!key.isValid()) continue;
		    
		    if (key.isAcceptable()) {
			ServerSocketChannel sschanal = (ServerSocketChannel) key
				.channel();
			SocketChannel sc = sschanal.accept();
			sc.configureBlocking(false);
			sc.register(selector, SelectionKey.OP_READ);
			System.out.println(sc.toString() + " has connected");

		    } else if (key.isReadable()) {
			try (SocketChannel sch = (SocketChannel) key.channel();) {
			    int readBytes = sch.read(buffer);
			    if (readBytes > -1) {
				buffer.flip();
				while (buffer.hasRemaining()) {
				    System.out.print((char) buffer.get());
				}
			    } else
				key.cancel();
			} catch (IOException e) {
			    e.printStackTrace();
			    key.cancel();
			}
			//key.interestOps(SelectionKey.OP_WRITE);
		    }

		     else if (key.isWritable()) {
			 SocketChannel sc = (SocketChannel) key.channel();
			 
			 sc.write(ByteBuffer.wrap("Okaaaaaaaaaaaa".getBytes()));
			 key.interestOps(SelectionKey.OP_READ);
		     }
		    
		}
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
