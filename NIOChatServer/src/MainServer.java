import java.io.IOException;


public class MainServer {

		public static void main(String[] args) throws IOException {
			NiochatServer server = new NiochatServer(4567);
			(new Thread(server)).start();
		}

}
