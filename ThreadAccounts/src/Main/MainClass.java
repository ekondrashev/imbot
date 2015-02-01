package Main;

import java.util.Random;

public class MainClass {
	public static void printArray(Account[] arr){
		for (Account account : arr) {
			System.out.print(account.i + " ");
		}
		System.out.println("");
	}
	
	public static int summArray(Account[] arr){
		int result = 0;
		for (Account account : arr) {
			result += account.i;
		}
		return result;
	}

	public static void main(String[] args) {
		
		Account[] accounts = new Account[10];
		Random rand = new Random();

		for (int k = 0; k < accounts.length; k++) {
			accounts[k] = new Account(rand.nextInt(100000));
		}
		
		printArray(accounts);
		System.out.println(summArray(accounts));
		
		Transfer t = new Transfer(accounts);
		
		for (int i = 0; i < 1000; i++) {
			new Thread(t).run();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printArray(accounts);
		System.out.println(summArray(accounts));
	}

}
