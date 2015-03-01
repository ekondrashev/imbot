import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

public class Nio2Client implements Runnable {
    private String userName;
    private String address;
    private int port;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private String message; // sent message

    public Nio2Client(String address, int port, String userName)
	    throws IOException {
	super();
	this.userName = userName;
	this.address = address;
	this.port = port;
	this.message = "";
    }

    @Override
    public void run() {
	try (AsynchronousSocketChannel asyncSChannel = AsynchronousSocketChannel
		.open()) {

	    if (asyncSChannel.isOpen()) {
		if (asyncSChannel.connect(new InetSocketAddress(address, port))
			.get() == null) {
		    System.out.println("You have connected to server");
		    message = "I'm have connected";
		    asyncSChannel.write(
			    ByteBuffer.wrap((userName + " - " + message)
				    .getBytes())).get();

		    new Thread() {
			public void run() {
			    try {
				while (asyncSChannel.read(buffer).get() != -1) {
				    buffer.flip();
				    while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get());
					Thread.sleep(50);
				    }
				    System.out.println("");
				    buffer.clear();
				}
				port = -1;
			    } catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			    }
			}
		    }.start();

		    // reading input stream (keyboard) and save it in field
		    // message
		    try (BufferedReader localReader = new BufferedReader(
			    new InputStreamReader(System.in));) {
			while (!(message = localReader.readLine())
				.equalsIgnoreCase("exit")) {
			    if (!message.equals("")) {
				asyncSChannel
					.write(ByteBuffer.wrap((userName
						+ " - " + message).getBytes()))
					.get();
			    }
			}
			message = "I'm just disconnected";
			asyncSChannel.write(
				ByteBuffer.wrap((userName + " - " + message)
					.getBytes())).get();
			// sending empty message in order to server will
			// know that this client exited
			asyncSChannel.write(ByteBuffer.wrap("".getBytes()))
				.get();
			while (port != -1){
			    //wait
			}

		    } catch (IOException | InterruptedException
			    | ExecutionException e) {
			e.printStackTrace();
		    }
		}
	    }

	    System.out.println("Good bye!!!");
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} catch (ExecutionException e) {
	    e.printStackTrace();
	}
    }
}
