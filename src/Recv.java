	import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
	
	public class Recv {
		
	    private final static String QUEUE_NAME = "Galina_Potemkina";
	    private final static String QUEUE_NAMEstop = "Galina_PotemkinaStop";
	
	    public static void main(String[] argv) throws Exception {
	
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
	      System.out.println(" [x] Received '" + message + "'");
	    }
	  }
	}
