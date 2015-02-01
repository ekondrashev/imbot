package Main;

import java.util.Random;

public class Transfer implements Runnable{
	private Random rand;
	private Account[] arr;
	
	public Transfer(Account[] arr){
		rand = new Random();
		this.arr = arr;
	}
	
	public void run(){
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		int sum = rand.nextInt(1000);
		int x1, y1;
		
		if(x > y){
			x1 = x;
			y1 = y;
		} else {
			x1 = y;
			y1 = x;
		}
		
		synchronized (arr[x1]) {
			synchronized (arr[y1]) {
				if(arr[x].i >= sum){
					arr[x].i -= sum;
					arr[y].i += sum;
				} else if(arr[y].i >= sum){
					arr[y].i -= sum;
					arr[x].i += sum;
				} else return;
			}
		}
		
	}
}
