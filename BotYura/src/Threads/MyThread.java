package Threads;

public class MyThread implements Runnable{
	public static int i = 0;
	public synchronized static void func() {
		for (int k = 0; k < 1000000; k++, i++);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(i);
	}
	public void run(){
		func();
		}
}
