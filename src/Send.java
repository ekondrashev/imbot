
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {
 
  private final static String QUEUE_NAME = "Galina_Potemkina";
  private final static String QUEUE_NAMEstop = "Galina_PotemkinaStop";
  
  public static void sendMessage(String mess) throws Exception {
             
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("217.146.253.33");
    factory.setPort(5672);    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    channel.basicPublish("", QUEUE_NAME, null, mess.getBytes());
    System.out.println(" [x] Sent '" + mess + "'");
    
    channel.close();
    connection.close();
  }
  
  public static void sendMessageStop(String mess) throws Exception {
      
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("217.146.253.33");
	    factory.setPort(5672);    
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAMEstop, false, false, false, null);
	    channel.basicPublish("", QUEUE_NAMEstop, null, mess.getBytes());
	    System.out.println(" [x] Sent '" + mess + "'");
	    
	    channel.close();
	    connection.close();
	  }
}
