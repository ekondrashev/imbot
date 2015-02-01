package Threads;

public class MyThread2{
	public static int i = 0;
	public void func1(){
		for (int i = 0; i < 1000000; i++, MyThread2.i++);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(MyThread.i);
	}

}
