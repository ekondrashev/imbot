package skypePackage;
import org.apache.log4j.Logger;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {
	private static final Logger log = Logger.getLogger(Send.class);
	
	private static String QUEUE_NAME = "QUEUE";
	private static String HOST = "127.0.0.1";

	Send(String queue, String host) {
		QUEUE_NAME = queue;
		HOST = host;
		log.info("Initialization class Send");
    	log.info("Initialization queue:"+QUEUE_NAME);
    	log.info("Initialization HOST:"+HOST);
	}

	public void sendMessage(String mess) throws Exception {
		log.debug("Start processing send");

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
			
			if (log.isDebugEnabled()) {
			    log.info(" [x] Sent '" + mess + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
	        log.error("Something failed", e);

		} finally {
			if (channel!=null) channel.close();
			if (connection!=null) connection.close();
			log.info("Close all connection");
		}
	}
}