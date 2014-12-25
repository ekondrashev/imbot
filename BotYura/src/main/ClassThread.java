package main;

import java.util.ArrayList;

public class ClassThread {

	public static void main(String[] args) {

		ArrayList<String> al = new ArrayList<>();
		
		A first = new A(al);
		B second = new B(al);
		C third = new C(al);
		Thread t = new Thread(first);
		Thread t2 = new Thread(second);
		Thread t3 = new Thread(third);
		t.start();
		t2.start();
		t3.start();
	}

}
