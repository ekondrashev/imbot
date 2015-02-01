package Threads;



public class MillionsThreads {

	public static void main(String[] args) {

		System.out.println("Variant 1, static metod:");
		MyThread[] a = new MyThread[10];
		Thread[] potoks = new Thread[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = new MyThread();
			potoks[i] = new Thread(a[i]);
			
		}
		for (int i = 0; i < potoks.length; i++)
			potoks[i].start();
		
		for (int i = 0; i < potoks.length; i++) {
			System.out.println(potoks[i].getName());
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
			for (int i = 0; i < potoks.length; i++) {
				if(potoks[i].isAlive())
					i = 0;
			}
			System.out.println("i = " + MyThread.i);
			
			MyThread.i = 0;
			
			System.out.println("Variant 2 synchronized block:");
			final MyThread2 b = new MyThread2();
			for (int i = 0; i < 10; i++) {
				new Thread(){
				public void run(){
					synchronized (b) {
						b.func1();
					}
				}
			}.start();
			}
			
	}

}
