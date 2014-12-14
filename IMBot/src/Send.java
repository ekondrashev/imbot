
import org.apache.log4j.Logger;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {
 
  private final static String QUEUE_NAME = "Galina_Potemkina";
  private static Logger log = Logger.getLogger(Send.class);
  
  public static void sendMessage(String mess) throws Exception {
  
	  
	  
    ConnectionFactory factory = new ConnectionFactory();
   // factory.setHost("217.146.253.39");
    factory.setHost("192.168.5.195");
    factory.setPort(5672);    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    channel.basicPublish("", QUEUE_NAME, null, mess.getBytes());
    //System.out.println(" [x] Sent '" + mess + "'");
    log.debug("[x] Sent '" + mess + "'");
    channel.close();
    connection.close();
  }
  
}
