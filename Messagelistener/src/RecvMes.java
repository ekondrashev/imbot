import java.util.ArrayList;

import java.util.Map;
import java.util.logging.Logger;




import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class RecvMes {

	private final static String QUEUE_NAME = "Galina_Potemkina";
	static private boolean chekSTOP = false;
	private static Logger log = Logger.getLogger(RecvMes.class.getName());
	
	public static void recvMessage() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("217.146.253.39");
		// factory.setHost("192.168.0.81");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		log.info("Waiting for messages");
		
			
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		while (chekSTOP == false) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			String[] messageArr = message.split("\\s");
			Map<String, String> map = MyPattern.parsingArgs(messageArr);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (map.get("cmd").equals("stop"))	{
					chekSTOP=true;
                	System.out.println("Process was stopped!");
                	log.info("Process was stopped!");
                   }
				//System.out.println(" [x] Received '" + entry.getValue() + "'");
				log.info("[x] Received '" + entry.getValue() + "'");
			}

		}
		
	}
	
	
    
    

}
