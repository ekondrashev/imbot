package main;



public class BotYura {
	
	//Bot —cmd=send_message -user_id=<id> -message=<message>
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		ComLineArg thisTimeArgs = parsingComLine(args);
		thisTimeArgs.printComLineArg();

	}
	
	public static ComLineArg parsingComLine(String[] args){
		
		ComLineArg carrentLineArg = new ComLineArg();
		
		if(args.length == 0){
			carrentLineArg.cmd = "help";
			return carrentLineArg;
		}

		for (String arg : args) {
			if (arg.startsWith(ComLineArg.cmdSignature)) {
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
			if(arg.startsWith("-user_id=")){
				carrentLineArg.userId = arg.substring("-user_id=".length(), arg.length());
				continue;
			}
			if(arg.startsWith("-message=")){
				carrentLineArg.userMessage = arg.substring("-message=".length(), arg.length());
				continue;
			}
		}

		
		return carrentLineArg;
		
	}
	

}
