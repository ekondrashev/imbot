import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MainAccount {

	public static void main(String[] args) throws InterruptedException {
		
		int[] accounts=new int[5];
		int summ=0;
		for (int i=0; i<5;i++)
		{
			accounts[i]=new Random().nextInt(10000);
			summ+=accounts[i];
		}

		for (int i=0; i<5;i++)
		{
			System.out.println("Account "+i+" summ "+accounts[i]);
		} 
		
	System.out.println("All summ "+summ);
		
	Account myAccounts=new Account(accounts);
		
	ExecutorService exec=Executors.newFixedThreadPool(5);
	
	for (int i=0; i<5;i++)
		exec.submit(myAccounts);

	exec.awaitTermination(5, TimeUnit.SECONDS);
	exec.shutdownNow();
	
	accounts=myAccounts.setArray();

	summ=0;
	for (int i=0; i<5;i++)
	{
		System.out.println("Account "+i+" summ "+accounts[i]);
		summ+=accounts[i];
	}
	
	System.out.println("All summ "+summ);
	
	}

}
