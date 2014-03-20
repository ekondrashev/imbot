package org.hillel.it.qsm.model.entities;

public class Test {
	public static void main(String[] args) {
		Server server1 = new Server();
		Client qsm = new Client ("example@qsm.com", "123xxx", server1);
		qsm.sendMessage("тема", "example@qsm.com", "Текст сообщения");
		qsm.getMessage();
		
	}
}
