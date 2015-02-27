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
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private LinkedList<SocketChannel> usersSChanalsList = new LinkedList<SocketChannel>();
    String stringMessage = null; // transmitted message

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

		    if (!key.isValid())
			continue;

		    if (key.isAcceptable()) {
			ServerSocketChannel sschanal = (ServerSocketChannel) key
				.channel();
			SocketChannel sc = sschanal.accept();
			sc.configureBlocking(false);
			sc.register(selector, SelectionKey.OP_READ);
			usersSChanalsList.add(sc); // add users SocketChannel to
						   // list

		    } else if (key.isReadable()) {
			SocketChannel sch = (SocketChannel) key.channel();
			buffer.clear();
			int readBytes = sch.read(buffer);
			if (readBytes > 0) {
			    buffer.flip();
			    byte[] message = new byte[readBytes];
			    buffer.get(message);
			    stringMessage = new String(message);
			}
			if (readBytes == -1 || readBytes == 0) {//if server has got the empty message 
			    usersSChanalsList.remove(sch);	//it's mean the user disconnected
			    sch.close();
			    key.cancel();
			    continue;
			}
			System.out.println(stringMessage);
			key.interestOps(SelectionKey.OP_WRITE);
		    }

		    else if (key.isWritable()) {
			SocketChannel sch = (SocketChannel) key.channel();
			for (SocketChannel channel : usersSChanalsList) {//transmit the message to all users
			    if (channel == sch)				//except user who sent this message
				continue;
			    buffer.flip();
			    channel.write(buffer);
			}
			key.interestOps(SelectionKey.OP_READ);

		    }

		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
