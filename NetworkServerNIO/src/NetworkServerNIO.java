import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;


public class NetworkServerNIO {
   public static final int PORT = 4567;
   
   public static void main(String[] args) throws IOException {
      String encoding = System.getProperty("file.encoding");
      Charset cs = Charset.forName(encoding);
      ByteBuffer buffer = ByteBuffer.allocate(48);
      SocketChannel ch = null;
      ServerSocketChannel ssc = ServerSocketChannel.open();
      Selector sel = Selector.open();
      boolean read = false, done = false;
      String response = null;
      try {
         ssc.configureBlocking(false);
         ssc.socket().bind(new InetSocketAddress(PORT));
         SelectionKey key = ssc.register(sel, SelectionKey.OP_ACCEPT);
         System.out.println("Server on port: " + PORT);
         while (!done) {
            sel.select();
			Iterator it = sel.selectedKeys().iterator();
            while (it.hasNext()) {
               SelectionKey skey = (SelectionKey) it.next();
               it.remove();
               if (skey.isAcceptable()) {
                  ch = ssc.accept();
                  System.out.println("Accepted connection from:"
                        + ch.socket());
                  ch.configureBlocking(false);
                  ch.register(sel, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
               }
               if (skey.isReadable() && !read) {
                   if (ch.read(buffer) > 0)
                      read = true;
                   CharBuffer cb = cs.decode((ByteBuffer) buffer.flip());
                   response = cb.toString();
                }
                if (skey.isWritable() && read) {
                   System.out.print("Echoing : " + response);
                   ch.write((ByteBuffer) buffer.rewind());
                   if (response.indexOf("END") != -1)
                      done = true;
                   buffer.clear();
                   read = false;
                }
            }
         }
      }
      finally {
         if (ch != null)
            ch.close();
         ssc.close();
         sel.close();
      }
   }
}