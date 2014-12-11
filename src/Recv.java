import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;


public class Recv {
	
    private static String QUEUE_NAME = "QUEUE";
    private static String HOST = "127.0.0.1";    
    
    static private boolean shutdownFlag = false;
    
    Recv(String queue, String host){
    	QUEUE_NAME=queue;
    	HOST=host;
    }
    
    public static void recive() throws Exception {
    	 Connection connection = null;
    	 ConnectionFactory factory;
try
{
	JOptionPane.showMessageDialog(null, "Hello, I'm here!!! My HOST="+HOST+" my queue="+QUEUE_NAME);
    factory = new ConnectionFactory();
    //factory.setHost("217.146.253.33");
    factory.setHost(HOST);
    connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    
    QueueingConsumer consumer = new QueueingConsumer(channel);
    channel.basicConsume(QUEUE_NAME, true, consumer);
    
    while (false==shutdownFlag) {
      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
      String message = new String(delivery.getBody());
      System.out.println(" [x] Received '" + message + "'");
      
      ParsingRegulary exec=new ParsingRegulary();
      String[] arg=message.split("\\s");
      Map<String,String> map=exec.decodeToMap(arg);

      if (map.get("command").equals("stop"))  {
    	  JOptionPane.showMessageDialog(null, "command Exit, I closing!!!");
    	  shutdownFlag=true;}
      
      else if (map.get("command").equals("print"))  {
    	  String ourMessage = "";
    	  for (Entry<String, String> elem : map.entrySet()) { 
				//System.out.println(elem.getKey() + ":" + elem.getValue());
    		  ourMessage=ourMessage+elem.getKey() + ":" + elem.getValue()+"\n";
			}
    	  JOptionPane.showMessageDialog(null, ourMessage);
      
    }
      else if (map.get("command").equals("ask") && map.containsKey("text")) {
    		ChatterBotFactory myFactory = new ChatterBotFactory();
    		ChatterBot skypeBot = myFactory.create(ChatterBotType.JABBERWACKY);
    		ChatterBotSession chatSession = skypeBot.createSession();
   			String myMessage = chatSession.think(map.get("text"));
   			JOptionPane.showMessageDialog(null, myMessage);
      }
  }
}	finally{
	connection.close();
	JOptionPane.showMessageDialog(null, "connection close!!!");
    }
 }
}