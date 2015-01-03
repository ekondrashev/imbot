

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class Recv implements Runnable{
	private static final Logger log = Logger.getLogger(Recv.class);

	private static String QUEUE_NAME = "QUEUE";
	private static String HOST = "127.0.0.1";
	private ConnectDB ourConnect;
	Recv(String queue, String host,ConnectDB ourConnect) {
		QUEUE_NAME = queue;
		HOST = host;
		this.ourConnect=ourConnect;
		log.info("Initialization class Recv");
		log.info("Initialization queue:" + QUEUE_NAME);
		log.info("Initialization HOST:" + HOST);
	}

	public void recive() throws Exception {
		Connection connection = null;
		ConnectionFactory factory;
		try {
			log.info("Start processing  My HOST=" + HOST + " my queue="
					+ QUEUE_NAME);
			factory = new ConnectionFactory();
			factory.setHost(HOST);
			connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out
					.println(" [*] Waiting for messages. To exit press CTRL+C");
			log.info(" [*] Waiting for messages. To exit press CTRL+C");

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(QUEUE_NAME, true, consumer);

			while (!Thread.currentThread().isInterrupted()) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(" [x] Received '" + message + "'");
				log.info(" [x] Received '" + message + "'");
				ParsingRegulary exec = new ParsingRegulary();
				String[] arg = message.split("\\^");
				Map<String, String> mapDecode = exec.decodeToMap(arg);

				if (mapDecode.get("command").equals("stop")) {
					Thread.currentThread().interrupt();
					log.info("command:stop");
				}

				else if (mapDecode.get("command").equals("print")) {
					String ourMessage = "";
					for (Entry<String, String> elem : mapDecode.entrySet()) {
						ourMessage = elem.getKey() + ":" + elem.getValue()
								+ "\n";
						log.info(ourMessage);
					}
				} 
				 else if (mapDecode.get("command").equals("ask") && mapDecode.get("text").equals("\"currency\"")){
					 log.info("get command:"+mapDecode.get("command"));
					 String answer=Currency.getCurrencyNBU();
					 Send send=new Send("FromDB",HOST);
					 String ourMessage="--cmd=answer^-key="+ mapDecode.get("key")+"^-text=\""+answer+"\"";
					 send.sendMessage(ourMessage);
				 } 
				 else if (mapDecode.get("command").equals("ask")) {
					log.info("get command:"+mapDecode.get("command"));
					String answer=Query.askToQuestion(this.ourConnect, mapDecode.get("apiname"), mapDecode.get("text"));
					Send send=new Send("FromDB",HOST);
					String ourMessage="--cmd=answer^-key="+ mapDecode.get("key")+"^-text=\""+answer+"\"";
					send.sendMessage(ourMessage);
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		} finally {
			if (connection != null)
				connection.close();
			log.info("close all connection");
		}
	}

	@Override
	public void run() {
		try {
			recive();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}