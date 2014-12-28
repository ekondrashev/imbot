package main;

import java.util.ArrayList;

public class A implements Runnable {
	ArrayList<String> al;

	public A(ArrayList<String> al) {
		this.al = al;
	}

	public void run() {
		synchronized (al) {
			func();
		}
	}

	public void func() {
		StringBuffer buf = new StringBuffer("a");
		for (int i = 0; i < 1000; i++) {
//			try{
//                Thread.sleep(10);
//            }catch(InterruptedException ex){
//                System.err.println("main::  Interrupted: "+ex.getMessage());
//            }
			al.add(buf.append("a").toString());
		}
		al.add("Stop");
	}
}
