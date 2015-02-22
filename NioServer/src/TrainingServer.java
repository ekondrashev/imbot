import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class TrainingServer implements Runnable {
    private int port;
    private LinkedList<SocketChannel> chanals = new LinkedList<SocketChannel>();
    private ByteBuffer buffer = ByteBuffer.allocate(64);

    public TrainingServer(int port) {
	this.port = port;
    }

    @Override
    public void run() {
	ServerSocketChannel ssch;
	try {
	    ssch = ServerSocketChannel.open();
	    ssch.socket().bind(new InetSocketAddress(port));
	    ssch.configureBlocking(false);
	    Selector selector = Selector.open();
	    ssch.register(selector, SelectionKey.OP_ACCEPT);
	    System.out
		    .println("Server is running and waits for a connection .");
	    new Thread(new Clients(chanals)).start();
	    while (true) {
		int readyChannels = selector.select();
		if (readyChannels == 0)
		    continue;
		Set<SelectionKey> selectedKeys = selector.selectedKeys();
		Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
		StringBuffer str = new StringBuffer();
		while (keyIterator.hasNext()) {
		    SelectionKey key = keyIterator.next();

		    if (key.isAcceptable()) {
			ServerSocketChannel sschanal = (ServerSocketChannel) key
				.channel();
			SocketChannel sc = sschanal.accept();
			sc.configureBlocking(false);
			sc.register(selector, SelectionKey.OP_READ);
			chanals.add(sc);
		    } else if (key.isReadable()) {
			SocketChannel sc = (SocketChannel) key.channel();
			int readBytes;
			while ((readBytes = sc.read(buffer)) > 0) {
			    buffer.flip();
			    while (buffer.hasRemaining())
				str.append(buffer.getChar());
			    buffer.clear();
			}
			if (readBytes == -1 || str.toString().equalsIgnoreCase("exit")) {
			    chanals.remove(sc);
			    sc.close();
			}
		    }
		    // else if (key.isWritable()) {// a channel is ready for
		    // writing}
		    keyIterator.remove();
		}
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
