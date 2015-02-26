
public class NioClientServer {

    public static void main(String[] args) {
	if (args.length == 0)
		return;
	System.out.println("Welcome!!!");
	if (args[0].equalsIgnoreCase("server"))
		new Thread(new TrainingServer(9000)).start();
	else if (args[0].equalsIgnoreCase("client"))
		new Thread(new TrainingClient("127.0.0.1", 9000, "Client")).start();
	else
		return;
    }

}
