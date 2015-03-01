
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
 
 
public class NIOClient {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        String string1 = "Sending a test message";
        String string2 = "Second message";
        SocketTest test1 = new SocketTest(string1);
        Thread thread = new Thread(test1);
        thread.start();
        //thread2.start();
    }
 
    static class SocketTest implements Runnable {
 
        private String message = "";
        private Selector selector;
 
 
        public SocketTest(String message){
            this.message = message;
        }
 
        @Override
        public void run() {
            SocketChannel channel;
            try {
                selector = Selector.open();
                channel = SocketChannel.open();
                channel.configureBlocking(false);
 
                channel.register(selector, SelectionKey.OP_CONNECT);
                channel.connect(new InetSocketAddress("127.0.0.1", 5678));
 
                while (!Thread.interrupted()){
 
                    selector.select(1000);
                     
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
 
                    while (keys.hasNext()){
                        SelectionKey key = keys.next();
                        keys.remove();
 
                        if (!key.isValid()) continue;
 
                        if (key.isConnectable()){
                            System.out.println("I am connected to the server");
                            connect(key);
                        }   
                        if (key.isWritable()){
                            write(key);
                        }
                        if (key.isReadable()){
                            read(key);
                        }
                    }   
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                close();
            }
        }
         
        private void close(){
            try {
                selector.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
 
        private void read (SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1000);
            readBuffer.clear();
            int length;
            try{
            length = channel.read(readBuffer);
            } catch (IOException e){
                System.out.println("Reading problem, closing connection");
                key.cancel();
                channel.close();
                return;
            }
            if (length == -1){
                System.out.println("Nothing was read from server");
                channel.close();
                key.cancel();
                return;
            }
            readBuffer.flip();
            byte[] buff = new byte[1024];
            readBuffer.get(buff, 0, length);
            System.out.println("Server said: "+new String(buff));
        }
 
        private void write(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            channel.write(ByteBuffer.wrap(message.getBytes()));
 
            // lets get ready to read.
            key.interestOps(SelectionKey.OP_READ);
        }
 
        private void connect(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            if (channel.isConnectionPending()){
                channel.finishConnect();
            }
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_WRITE);
        }
    }
}