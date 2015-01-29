package TenMillion;

public class NewTread extends Thread{

	public void run(){
		synchronized (tenThreads){
			tenThreads.millionCount();
		}
	}
}
