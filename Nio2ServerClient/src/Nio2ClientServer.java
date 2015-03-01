import java.io.IOException;
import java.util.LinkedList;

public class Nio2ClientServer {
    private static LinkedList<String> users = new LinkedList<String>();

    public static void main(String[] args) throws IOException {
	if (args.length == 0
		|| (args[0].equalsIgnoreCase("client") && args.length < 2))
	    return;

	System.out.println("Welcome!!!");
	if (args[0].equalsIgnoreCase("server"))
	    new Thread(new Nio2Server(9000)).start();
	else if (args[0].equalsIgnoreCase("client")) {
	    if (users.contains(args[1])) {
		System.out.println("User with this name already exist");
		return;
	    }
	    users.add(args[1]);
	    new Thread(new Nio2Client("127.0.0.1", 9000, args[1])).start();
	} else
	    return;
    }

}
