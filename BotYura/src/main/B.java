package main;

import java.util.ArrayList;

public class B implements Runnable{
	ArrayList<String> al;
	public B(ArrayList<String> al){
		this.al = al;
	}
public void run(){
	int i = -1;
	do {
		i++;
		System.out.println(al.get(i));
	} while (al.get(i).equals("Stop") == false);
		
}
}
