package TenMillion;

public class OneMillion extends Thread{
	public static int x=0;
	public void millionCount() {
		
		for (int i = 0; i < 1000000; i++,x++);
		
		
		System.out.println(x);

	}
}
