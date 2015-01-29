import java.util.Random;


public class Main {
	
	static public int summ(Account[] arr){
		int sum = 0;
		for (Account i : arr) {
			sum += i.money;
		}
		return sum;
	}
	
	static public void printIntArray(Account[] arr){
		for (Account i : arr) {
			System.out.print(i.money + " ");
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		Account[] accounts = new Account[10];
		for(int i = 0; i < accounts.length; i++){
			accounts[i] = new Account(rand.nextInt(1000));
		}
		printIntArray(accounts);
		System.out.println(summ(accounts));
		
		Transfer t = new Transfer(accounts);
		
		for (int i = 0; i < 10000; i++) {
			new Thread(t).start();
		}
		
		printIntArray(accounts);
		System.out.println(summ(accounts));
	}

}
