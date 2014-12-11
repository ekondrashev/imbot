import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {

	private static String QUEUE_NAME = "QUEUE";
	private static String HOST = "127.0.0.1";

	Send(String queue, String host) {
		QUEUE_NAME = queue;
		HOST = host;
	}

	public static void sendMessage(String mess) throws Exception {
		Connection connection = null;
		Channel channel = null;

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(HOST);
			factory.setPort(5672);
			connection = factory.newConnection();
			channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			channel.basicPublish("", QUEUE_NAME, null, mess.getBytes());
			System.out.println(" [x] Sent '" + mess + "'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			channel.close();
			connection.close();
		}
	}
}