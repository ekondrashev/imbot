
import java.util.List;

public class Reader implements Runnable {
	private List c;
	public void run() {
		int i = 0;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (i < c.size()) 
		{
			c.get(i);
			i++;
		}
		System.out.println(c.size());
	}
	Reader (List b) {
		this.c = b;	
	}
}
