import java.util.List;


public class MyThread implements Runnable {
	public void run() {

		int i=0;
		while (i < 1000){
			i++;
			HelloWorld.a++;
	}
		System.out.println("a="+(HelloWorld.a));
	}

}
