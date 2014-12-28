import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.skype.Chat;

public class Recv implements Runnable {

	private final static String QUEUE_NAME = "Galina_Potemkina";
	static private boolean checkSTOP = false;
	private static Logger log = Logger.getLogger(Recv.class);
	Map<String, Chat> map = new LinkedHashMap<String, Chat>();

	Recv(Map<String, Chat> map) {
		this.map = map;
	};

	@Override
	public void run() {
		try {
			recvMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void recvMessage() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		//factory.setHost("217.146.253.33");
		factory.setHost("192.168.5.192");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		while (checkSTOP == false) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			String[] messageArr = message.split("\\s");
			Map<String, String> mapPars = Exec.parsingArgs(messageArr);
			log.debug("[x] Received '" + messageArr);
			for (Map.Entry<String, String> entry : mapPars.entrySet()) {
				if (mapPars.get("cmd").equals("stop")) {
					checkSTOP = true;
					System.out.println("Process was stopped!");
					log.debug("Process was stopped!");
					channel.close();
					connection.close();
				} else if (mapPars.get("cmd").equals("send_message")) {
					String ourID = mapPars.get("user_id");
					String ourMes = mapPars.get("message");

					final Chat chatterup = map.get(ourID);
					chatterup.send(ourMes);
                    map.remove(ourID);
				}
				log.debug("[x] Received '" + entry.getValue() + "'");

			}

		}
	}

}
