import java.util.Random;
import java.util.concurrent.ForkJoinPool;


public class Main {
	public static void main(String args[]) {
	Fibonacci f = new Fibonacci(50);
	final ForkJoinPool mainPool = new ForkJoinPool();
	int result = mainPool.invoke(f);
	System.out.println(result);
	Random r = new Random();
	r.nextInt();

	
	}
	
}
