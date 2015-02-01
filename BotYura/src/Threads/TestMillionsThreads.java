package Threads;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMillionsThreads {

	@Test
	public void test() {
		MyThread2.i = 0;
		final MyThread2 b = new MyThread2();
		Thread[] k = new Thread[10];
		int i;
		for (i = 0; i < 10; i++) {
			k[i] = new Thread(){
			public void run(){
				synchronized (b) {
					b.func1();
				}
			}
		};
		k[i].start();
		}
		for (i = 0; i < 10; i++) {
			try {
				k[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(MyThread2.i);
		assertEquals(MyThread2.i, 10000000);
	}

}
