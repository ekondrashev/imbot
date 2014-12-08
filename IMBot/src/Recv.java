import java.util.ArrayList;
import java.util.Map;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class Recv {

	private final static String QUEUE_NAME = "Galina";

	public static void recvMessage(String mess) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("217.146.253.33");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			//String[] messageArr = divide(message);
			String[] messageArr = message.split("");
			Map<String, String> map = Exec.parsingArgs(messageArr);
			for (Map.Entry<String, String> entry : map.entrySet()) {
                if (map.containsKey("cmd") && map.containsValue("stop")){
                	System.out.println("Process was stopped!");
                    break;}
                else	
				System.out.println(" [x] Received '" + entry.getValue() + "'");
			}

		}
	}

	public static String[] divide(String s) {
		ArrayList<String> tmp = new ArrayList<String>();
		int i = 0;

		for (int j = 0; j < s.length(); j++) {
			if (s.charAt(j) == ' ') {
				if (j > i) {
					tmp.add(s.substring(i, j));
				}
				i = j + 1;
			}
		}
		if (i < s.length()) {
			tmp.add(s.substring(i));
		}
		return tmp.toArray(new String[tmp.size()]);
	}
}
