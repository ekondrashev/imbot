package RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Receiving {

	/**
	 * @param args
	 */
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args)throws java.io.IOException,
    java.lang.InterruptedException {
		// TODO Auto-generated method stub
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("217.146.253.33");
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
