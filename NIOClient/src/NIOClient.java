import java.net.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class NIOClient {
   public static void main(String[] args) throws IOException {
      int clPrt = 4567;
      SocketChannel sc = SocketChannel.open();
      Selector sel = Selector.open();
      try {
         sc.configureBlocking(false);
         sc.register(sel, SelectionKey.OP_CONNECT|SelectionKey.OP_READ|SelectionKey.OP_WRITE);
         sc.connect(new InetSocketAddress(clPrt));
         int i = 0;
         boolean written = false, done = false;
         String encoding = System.getProperty("file.encoding");
         Charset cs = Charset.forName(encoding);
         ByteBuffer buf = ByteBuffer.allocate(16);
         while (!done) {
            sel.select();
            Iterator<SelectionKey> it = sel.selectedKeys().iterator();
            while (it.hasNext()) {
               SelectionKey key = (SelectionKey) it.next();
               it.remove();
               sc = (SocketChannel) key.channel();
               if (key.isConnectable() && !sc.isConnected()) {
                  InetAddress addr = InetAddress.getByName(null);
                  boolean success = sc.connect(new InetSocketAddress(
                        addr, clPrt));
                  if (!success)
                     sc.finishConnect();
               }
               if (key.isReadable() && written) {
                  if (sc.read((ByteBuffer) buf.clear()) > 0) {
                     written = false;
                     String response = cs
                           .decode((ByteBuffer) buf.flip()).toString();
                     System.out.print(response);
                     if (response.indexOf("END") != -1)
                        done = true;
                  }
               }
               if (key.isWritable() && !written) {
                  if (i < 10)
                     sc.write(ByteBuffer.wrap(new String("howdy " + i
                           + 'n').getBytes()));
                  else if (i == 10)
                     sc.write(ByteBuffer.wrap(new String("ENDn")
                           .getBytes()));
                  written = true;
                  i++;
               }
            }
         }
      }
      finally {
         sc.close();
         sel.close();
      }
   }
} // /:~