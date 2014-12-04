package RabbitMQ;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class start {

	/**
	 * @param args
	 */
	
	
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args)throws java.io.IOException {
		// TODO Auto-generated method stub
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("217.146.253.33");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    String message = "Hello World from Yura! I'm here)))";
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	    channel.close();
	    connection.close();

	}

}
