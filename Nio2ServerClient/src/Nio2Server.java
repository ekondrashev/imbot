import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Nio2Server implements Runnable {
    private int port;
    private LinkedList<AsynchronousSocketChannel> usersSChanalsList = new LinkedList<AsynchronousSocketChannel>();
    String stringMessage = null; // transmitted message
    ExecutorService executor = Executors.newCachedThreadPool(Executors
	    .defaultThreadFactory());

    public Nio2Server(int port) {
	this.port = port;
    }

    private void closeASChannel() throws IOException {
	for (AsynchronousSocketChannel channel : usersSChanalsList) {
	    if (channel != null)
		channel.close();
	}
    }

    @Override
    public void run() {
	try (AsynchronousServerSocketChannel asyncSSChannel = AsynchronousServerSocketChannel
		.open()) {

	    if (asyncSSChannel.isOpen()) {
		asyncSSChannel.bind(new InetSocketAddress(port));

		System.out
			.println("Server is running and waits for a connection .");
		while (!Thread.currentThread().isInterrupted()) {
		    Future<AsynchronousSocketChannel> asyncSCFuture = asyncSSChannel
			    .accept();
		    try {
			AsynchronousSocketChannel asyncSChannel = asyncSCFuture
				.get();
			usersSChanalsList.add(asyncSChannel);
			Runnable oneThread = new Runnable() {
			    @Override
			    public void run() {
				int readBytes;
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				try {
				    while ((readBytes = asyncSChannel.read(
					    buffer).get()) != -1) {
					buffer.flip();
					byte[] message = new byte[readBytes];
					buffer.get(message);
					stringMessage = new String(message);
					System.out.println(stringMessage);

					synchronized (buffer) {
					    for (AsynchronousSocketChannel channel : usersSChanalsList) {
					    // except user who sent this message
					    if (channel == asyncSChannel)
						continue;
					    buffer.flip();
					    channel.write(buffer);
					}
					}
					// transmit the message to all users
					buffer.clear();
				    }
				    try {
					    asyncSChannel.write(ByteBuffer.wrap(("").getBytes()))
					    .get();
					} catch (InterruptedException
						| ExecutionException e1) {
					    e1.printStackTrace();
					}
				} catch (InterruptedException
					| ExecutionException e1) {
				    e1.printStackTrace();
				}
				usersSChanalsList.remove(asyncSChannel);
				try {
				    asyncSChannel.close();
				} catch (IOException e) {
				    e.printStackTrace();
				}
			    }
			};
			executor.execute(oneThread);
		    } catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			executor.shutdown();
			while (!executor.isTerminated()) {
			}
			closeASChannel();
		    }
		}
	    } else {
		System.out.println("Server can't be open");
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		closeASChannel();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
