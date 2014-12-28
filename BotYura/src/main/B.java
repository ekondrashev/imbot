package main;

import java.util.ArrayList;

public class B implements Runnable{
	ArrayList<String> al;
	public B(ArrayList<String> al){
		this.al = al;
	}
public void run(){
	synchronized(al){
	while(true){
		if(al.get(al.size()-1) != null){
			System.out.println(al.get(al.size()-1));
		}
		else
			continue;
		if(al.get(al.size()-1).equals("Stop")){
			break;
		}
		}
	}
	}
	
//	while(i >= 0){
//		al.remove(i);
//		i--;
//	}
	
}
