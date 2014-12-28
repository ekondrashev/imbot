package main;

import java.util.ArrayList;


public class A implements Runnable{
		ArrayList<String> al;
		public A(ArrayList<String> al){
			this.al = al;
		}
public void run(){
	func();
	}
	synchronized public void func(){
		StringBuffer buf = new StringBuffer("a");
		for (int i = 0; i < 1000; i++) {
			al.add(buf.append("a").toString());
			}
		al.add("Stop");
}
}



