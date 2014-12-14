import java.util.Map;
import org.apache.log4j.Logger;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class RecvMes {

	private final static String QUEUE_NAME = "Galina_Potemkina";
	static private boolean checkSTOP = false;
	private static Logger log = Logger.getLogger(RecvMes.class);
    
	public static void recvMessage() throws Exception {

				
		ConnectionFactory factory = new ConnectionFactory();
		//factory.setHost("217.146.253.39");
		// factory.setHost("192.168.0.81");
		factory.setHost("192.168.5.195");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		log.debug("Waiting for messages");

			
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		while (checkSTOP == false) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			String[] messageArr = message.split("\\s");
			Map<String, String> map = MyPattern.parsingArgs(messageArr);
			log.debug("[x] Received '" + messageArr);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (map.get("cmd").equals("stop"))	{
					checkSTOP=true;
                	System.out.println("Process was stopped!");
                	log.debug("Process was stopped!");
                    channel.close();
                    connection.close();
                   }
				//System.out.println(" [x] Received '" + entry.getValue() + "'");
				log.debug("[x] Received '" + entry.getValue() + "'");
			}
        
		}
		
	}
	
	
    
    

}
