package TenMillion;

public class oneMillion extends Thread{
	public void millionCount() {
		int x = 0;
		for (int i = 0; i < 1000000; i++) {
			x = 0 + i;
		}
        
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(x);

	}

	public void run() {
		millionCount();
	}
}
