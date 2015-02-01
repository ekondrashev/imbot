package Main;


public class Transfer implements Runnable{
	private Account[] arr;
	int x;
	int y;
	int sum;
	
	public Transfer(Account[] arr, int x, int y, int sum){
		this.arr = arr;
		this.x = x;
		this.y = y;
		this.sum = sum;
	}
	
	public void run(){
		
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
//				if(arr[x].i >= sum){
//					arr[x].i -= sum;
//					arr[y].i += sum;
//				} else if(arr[y].i >= sum){
//					arr[y].i -= sum;
//					arr[x].i += sum;
//				} else return;
				
				if(arr[x].i >= sum){
					arr[x].i -= sum;
					arr[y].i += sum;
				} else return;
			}
		}
		
	}
}
