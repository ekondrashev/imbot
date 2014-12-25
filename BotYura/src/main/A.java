package main;

import java.util.ArrayList;


public class A implements Runnable{
		ArrayList<String> al;
		public A(ArrayList<String> al){
			this.al = al;
		}
public void run(){
	
	}
	public void func(){
		StringBuffer buf = new StringBuffer("a");
		for (int i = 0; i < 100; i++) {
//			if(i == 15|| i == 25){
//				al.add("Stop");
//				continue;
//			}
			al.add(buf.append("a").toString());
	}
	synchronized  A{
		
	};
}
}



