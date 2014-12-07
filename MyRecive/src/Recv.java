import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class Recv {
	
    private final static String QUEUE_NAME = "Potemkin";
    
    static private boolean shutdownFlag = false;
    
    public static void recive() throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("217.146.253.33");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    
    QueueingConsumer consumer = new QueueingConsumer(channel);
    channel.basicConsume(QUEUE_NAME, true, consumer);
    
    while (false==shutdownFlag) {
      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
      String message = new String(delivery.getBody());
      System.out.println(" [x] Received '" + message + "'");
      
      Exec2 exec=new Exec2();
      String[] arg=message.split("\\s");
      Map<String,String> map=exec.decodeToMap(arg);

      if (map.get("command").equals("stop"))  {shutdownFlag=true;}
      else if (map.get("command").equals("print"))  {
    	  String ourMessage = "";
    	  for (Entry<String, String> elem : map.entrySet()) { 
				//System.out.println(elem.getKey() + ":" + elem.getValue());
    		  ourMessage=ourMessage+elem.getKey() + ":" + elem.getValue()+"\n";
			}
    	  JOptionPane.showMessageDialog(null, ourMessage);
      
    }
  }
 }
}