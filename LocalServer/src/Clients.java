import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class Clients implements Runnable {
    private HashMap<String, Socket> list;
    private String clientName;

    public Clients(HashMap<String, Socket> list, String clientName) {
	super();
	this.list = list;
	this.clientName = clientName;
    }

    @Override
    public void run() {

	try (BufferedReader in = new BufferedReader(new InputStreamReader(((Socket)list
		.get(clientName)).getInputStream()))) {
	    String str = null;
	    while (list.size() > 0 && !(str = in.readLine()).equals("exit")) {
//		for (Map.Entry<String, Socket> entry : list.entrySet()) {
//		    if (entry.getKey().equals(clientName))
//			continue;
//		    try (PrintWriter out = new PrintWriter(
//			    new OutputStreamWriter(entry.getValue()
//				    .getOutputStream()), true)) {
//			out.println(str);
//		    }
//		}

		System.out.println(clientName + " - " + str);
	    }
	    System.out.println("Good Bye " + clientName);
	    list.get(clientName).close();
	    list.remove(clientName);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
