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
    private String message;

    public TrainingClient(String address, int port, String userName) throws IOException {
	super();
	this.userName = userName;
	this.address = address;
	this.port = port;
	this.message = "";
    }
    
    private void listenTipe(){
	Thread letter = new Thread() {
		public void run() {
		    try (BufferedReader localReader = new BufferedReader(
			    new InputStreamReader(System.in));) {
			while (!(message = localReader.readLine()).equalsIgnoreCase("exit")) {
				
			    }

		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	    };
	    letter.start();
    }

    @Override
    public void run() {
	try (Selector selector = Selector.open();
		SocketChannel schannel = SocketChannel.open();) {

	    schannel.configureBlocking(false);
	    schannel.register(selector, SelectionKey.OP_CONNECT);
	    schannel.connect(new InetSocketAddress(address, port));
	    System.out.println("You have connected to server");
	    listenTipe();

	    while (!Thread.currentThread().isInterrupted()) {
		int readyChannels = selector.select(1000);
		if (!message.equals("")){
		    schannel.write(ByteBuffer.wrap(message.getBytes()));
		    message = "";
		}
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
			
			key.interestOps(SelectionKey.OP_READ);
		    
		    } else if (key.isReadable()) {// reading
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
		    }
		}
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
