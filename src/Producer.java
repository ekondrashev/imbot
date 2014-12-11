import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class Producer {

    private final static String QUEUE_NAME = "hello Sneight";

    public static void main( String[] argv) throws IOException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("217.146.253.39");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent" + "'");
        channel.close();
        connection.close();
    }
}