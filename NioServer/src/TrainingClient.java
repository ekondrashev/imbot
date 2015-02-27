import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class TrainingClient implements Runnable {
    private String userName;
    private String address;
    private int port;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private String message; // sent message

    public TrainingClient(String address, int port, String userName)
	    throws IOException {
	super();
	this.userName = userName;
	this.address = address;
	this.port = port;
	this.message = "";
    }

    private void listenTipe() {// reading input stream (keyboard) and save it in
			       // field message
	new Thread() {
	    public void run() {
		try (BufferedReader localReader = new BufferedReader(
			new InputStreamReader(System.in));) {
		    while (!(message = localReader.readLine())
			    .equalsIgnoreCase("exit")) {

		    }
		    port = -1;// using field port as a key for stop thread and
			      // closing client
		    message = "I'm just disconnected";
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}.start();
    }

    @Override
    public void run() {
	try (Selector selector = Selector.open();
		SocketChannel schannel = SocketChannel.open();) {

	    schannel.configureBlocking(false);
	    schannel.register(selector, SelectionKey.OP_CONNECT);
	    schannel.connect(new InetSocketAddress(address, port));

	    while (!Thread.currentThread().isInterrupted()) {
		if (!message.equals("")) { // never send empty message
		    schannel.keyFor(selector)
			    .interestOps(SelectionKey.OP_WRITE);
		}
		int readyChannels = selector.select(1000);
		if (readyChannels == 0)
		    continue;
		Iterator<SelectionKey> keyIterator = selector.selectedKeys()
			.iterator();

		while (keyIterator.hasNext()) {
		    SelectionKey key = keyIterator.next();
		    keyIterator.remove();

		    if (!key.isValid())
			continue;

		    if (key.isConnectable()) {// connection
			SocketChannel sch = (SocketChannel) key.channel();
			if (sch.isConnectionPending()) {
			    sch.finishConnect();
			}
			sch.configureBlocking(false);
			System.out.println("You have connected to server");
			listenTipe();
			message = "I'm have connected";

		    } else if (key.isReadable()) {// reading
			SocketChannel sch = (SocketChannel) key.channel();
			buffer.clear();
			int readBytes = sch.read(buffer);
			if (readBytes > -1) {
			    buffer.flip();
			    while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get());
				Thread.sleep(50);
			    }
			    System.out.println("");
			try (SocketChannel sch = (SocketChannel) key.channel();) {
			    int readBytes = sch.read(buffer);
			    if (readBytes > -1) {
				buffer.flip();
				while (buffer.hasRemaining()) {
				    System.out.print((char) buffer.get());
				}
			    } //else
				//key.cancel();
			} catch (IOException e) {
			    e.printStackTrace();
			    //key.cancel();
			}

		    } else if (key.isWritable()) {
			SocketChannel sch = (SocketChannel) key.channel();
			sch.write(ByteBuffer.wrap((userName + " - " + message)
				.getBytes()));
			if (port == -1) {
			    sch.write(ByteBuffer.wrap(("").getBytes()));//sending empty message in order to server will 
			    Thread.currentThread().interrupt();		//know that this client exited
			    continue;
			}

			message = "";
			key.interestOps(SelectionKey.OP_READ);
		    }
		}
	    }

	    System.out.println("Good bye!!!");
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

    }

}
