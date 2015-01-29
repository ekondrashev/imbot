import java.util.List;


public class Writer implements Runnable {
	private  List b;
	public void run() {
		int i=0;
		while (i < 100) {
			b.add(i);
			i++;
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	Writer (List b) {
		this.b=b;
				
	}
	
	
}
