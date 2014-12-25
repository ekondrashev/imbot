import java.util.ArrayList;
import java.util.List;


public class Main {
	public static void main (String [] args) 
	{
		List list= new ArrayList();
		Writer a = new Writer(list);
		Reader b = new Reader(list);
		Thread c = new Thread(a);
		Thread v = new Thread(b);
		c.start();
		v.start();
	}

}
