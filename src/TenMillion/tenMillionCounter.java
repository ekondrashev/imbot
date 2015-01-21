package TenMillion;

public class tenMillionCounter extends Thread{
	public static void main(String[] args ){
		final oneMillion tenThreads = new oneMillion();
			for (int i=0; i>10; i++){
				new Thread(){
					public void run(){
						synchronized (tenThreads){
							tenThreads.millionCount();
						}
					}
					
				}.start();
			}
		
	}	
}
