package main;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import org.apache.log4j.Logger;


public class BotYura {
	
	//Bot —cmd=send_message -user_id=<id> -message=<message>
	private final static String QUEUE_NAME = "hello";
	
	private static final Logger logger = Logger.getLogger(BotYura.class.getName());


	public static void main(String[] args) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		 
		ComLineArg thisTimeArgs = parsingComLine(args);

		switch(thisTimeArgs.cmd){
		case "send_message":
			if(thisTimeArgs.userId != null && thisTimeArgs.userMessage != null){
				SendCommand(thisTimeArgs.userId + ":" + thisTimeArgs.userMessage);
			}
			else{
				if(thisTimeArgs.userId == null){
					System.out.println("You did not input a username");
				}
				if(thisTimeArgs.userMessage == null){
					System.out.println("You did not write a message");
				}
				System.out.println(ComLineArg.USAGE);
			}
			break;
		case "receive":
			ReceiveMessage();
			break;
		case "stop":
			SendCommand("stop");
			break;
		case "help":
		default:
			if(thisTimeArgs.errorMessage != null)
				System.out.println(thisTimeArgs.errorMessage + "\n");
			System.out.println(ComLineArg.USAGE);
			
			logger.info(thisTimeArgs.errorMessage);
		}
	}
	
// this function send some command	
	public static void SendCommand(String strCmd)throws java.io.IOException,
    java.lang.InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("217.146.253.39");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    
	    channel.basicPublish("", QUEUE_NAME, null, strCmd.getBytes());
	    System.out.println("Your message '"  + strCmd + "' has been sent");
	    channel.close();
	    connection.close();
	}
	
// 	this function receive some message  end handle it
	public static void ReceiveMessage() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException{
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("217.146.253.39");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    
	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    channel.basicConsume(QUEUE_NAME, true, consumer);
	    
	    System.out.println("Received messages: \n");
	    
	    boolean key = true;   

	    while (key) {
	      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	      String message = new String(delivery.getBody());
	      if(message.equals("stop")){
	    	  System.out.println("Receiving messages is stopped");
	    	  channel.close();
	    	  connection.close();
	    	  key = false;
	      }
	      if(message.length() > 0 && key == true)
	    	  System.out.println(message);
	    }
	}
	
	public static ComLineArg parsingComLine(String[] args){
		Pattern p = Pattern.compile("^-[a-z_]{3,50}=");
		Matcher m;		 
		
		ComLineArg carrentLineArg = new ComLineArg();
		
		if(args.length == 0){
			carrentLineArg.errorMessage = "Not found any command!";
			carrentLineArg.cmd = "help";
			return carrentLineArg;
		}

		for (String arg : args) {
			
			m = p.matcher(arg);
			if(!m.find()){
				carrentLineArg.errorMessage = "\'" + arg + "\'" + "is not a botYura command!";
				carrentLineArg.cmd = "help";
				return carrentLineArg;
			}
			
			if (m.group().equals(ComLineArg.cmdSignature)) {
				carrentLineArg.cmd = arg.substring(ComLineArg.cmdSignature.length(), arg.length());
				switch(carrentLineArg.cmd){
				case "send_message":
					break;
				case "receive":
					break;
				case "stop":
					break;
				case "help":
					break;
				default:
					carrentLineArg.errorMessage = "\'" + arg + "\'" + "is not a botYura command!";
					carrentLineArg.cmd = "help";
				}
				continue;
			}
			if(m.group().equals("-user_id=")){
				carrentLineArg.userId = arg.substring("-user_id=".length(), arg.length());
				continue;
			}
			if(m.group().equals("-message=")){
				carrentLineArg.userMessage = arg.substring("-message=".length(), arg.length());
				continue;
			}
		}

		
		return carrentLineArg;
		
	}
	

}
