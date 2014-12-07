import java.util.Map;


import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class RecvMes {

	private final static String QUEUE_NAME = "Galina_Potemkina";

	public static void recvMessage() throws Exception {

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
			String[] messageArr = message.split("");
			Map<String, String> map = MyPattern.parsingArgs(messageArr);
			for (Map.Entry<String, String> entry : map.entrySet()) {
                if (map.containsKey("cmd") && map.containsValue("stop")){
                	System.out.println("Process was stopped!");
                    break;}
                else	
				System.out.println(" [x] Received '" + entry.getValue() + "'");
			}

		}
	}

		
	
}
