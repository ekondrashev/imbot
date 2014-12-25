package main;

import java.util.ArrayList;

public class C implements Runnable{
	ArrayList<String> al;
	public C(ArrayList<String> al){
		this.al = al;
	}
public void run(){
	al.remove(0);
}
}
