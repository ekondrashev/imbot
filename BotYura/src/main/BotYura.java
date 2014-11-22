package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class BotYura {
	
	//Bot —cmd=send_message -user_id=<id> -message=<message>
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		ComLineArg thisTimeArgs = parsingComLine(args);
		thisTimeArgs.printComLineArg();

	}
	
	public static ComLineArg parsingComLine(String[] args){
		Pattern p = Pattern.compile("^-[a-z_]{3,50}=");;
		Matcher m;		 
		
		ComLineArg carrentLineArg = new ComLineArg();
		
		if(args.length == 0){
			System.out.println("Not found any command!");
			carrentLineArg.cmd = "help";
			return carrentLineArg;
		}

		for (String arg : args) {
			
			m = p.matcher(arg);
			if(!m.find()){
				System.out.println("\'" + arg + "\'" + "is not a botYura command!");
				carrentLineArg.cmd = "help";
				return carrentLineArg;
			}
			
			if (m.group().equals(ComLineArg.cmdSignature)) {
				carrentLineArg.cmd = arg.substring(ComLineArg.cmdSignature.length(), arg.length());
				switch(carrentLineArg.cmd){
				case "send_message":
					break;
				case "help":
					break;
				default:
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
